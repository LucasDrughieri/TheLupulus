package app.service;

import app.entity.Beer;
import app.entity.Container;
import app.entity.order.OrderState;
import app.entity.user.User;
import app.entity.user.UserRole;
import app.infraestructure.Response;
import app.model.order.Item;
import app.model.order.Order;
import app.repository.BeerRepository;
import app.repository.ContainerRepository;
import app.repository.ItemRepository;
import app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository _orderRepository;

    @Autowired
    ItemRepository _itemRepository;

    @Autowired
    BeerRepository _beerRepository;

    @Autowired
    ContainerRepository _containerRepository;

    public Response create(List<Item> itemsModels, User user){

        Response response = new Response();
        if(user.getRole() != UserRole.NORMAL_USER.getCode()) {
            response.addError("Solo un usuario tipo ‘cliente’ puede crear pedidos.");
            return response;
        }

        // First create order
        app.entity.order.Order order = new app.entity.order.Order();
        order.setDate(new Date());
        order.setUserId(user);
        order.setVisible(true);
        order.setStatus(OrderState.PENDING.getCode());

        try{
            order = _orderRepository.save(order);
        } catch (Exception e){
            response.addError("Ocurrió un error al intentar crear el pedido.");
            return response;
        }

        List<app.entity.order.Item> items = new ArrayList<>();
        Float total = 0.0f;

        // Then create items and assign them to the order
        for(Item itemModel: itemsModels) {
            app.entity.order.Item itemEntity = new app.entity.order.Item();

            Beer beer = _beerRepository.getById(itemModel.getIdCerveza());
            itemEntity.setBeerId(beer);

            Container container = _containerRepository.getById(itemModel.getIdContenedor());

            // cantidad <= contenedor.quantity && contenedor.capacity * cantidad <= cerveza.quantity
            if(itemModel.getCantidad() >= container.getQuantity() || beer.getQuantity() <= container.getCapacity() * itemModel.getCantidad()) {
                for(app.entity.order.Item item: items) {
                    _itemRepository.delete(item.getItemId());
                }
                _orderRepository.delete(order.getId());
                response.addError("No se puede satisfacer el pedido con el stock existente.");
                return response;
            }

            container.setQuantity(container.getQuantity() - itemModel.getCantidad().intValue());
            beer.setQuantity(beer.getQuantity() - container.getCapacity() * itemModel.getCantidad().intValue());

            _containerRepository.save(container);
            _beerRepository.save(beer);

            itemEntity.setContainerId(container);

            itemEntity.setCantidad(itemModel.getCantidad());

            itemEntity.setOrderId(order);

            try{
                itemEntity = _itemRepository.save(itemEntity);
            } catch (Exception e){
                response.addError("Ocurrió un error al intentar crear un item para el pedido.");
                return response;
            }

            items.add(itemEntity);

            total += itemEntity.getPrecio();
        }

        // Return response as it's specified in swagger
        Order orderModel = new Order();
        orderModel.setCliente(user);
        orderModel.setEstado(OrderState.PENDING.getCode());
        orderModel.setIdPedido(order.getId());
        orderModel.setItems(items);
        orderModel.setPagado(false);
        orderModel.setTotal(total);

        response.data = orderModel;
        response.addSuccess("Orden creada satisfactoriamente.");
        return response;

    }

    public Response getAll(){
        Response response = new Response();

        try{
            List<app.entity.order.Order> orders = _orderRepository.getAll();

            if (CollectionUtils.isEmpty(orders)){
                response.data = new ArrayList<>();
                response.addSuccess("No hay pedidos.");
                return response;
            }

            response.data = orders;
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un error al obtener los pedidos.");
            return response;
        }
    }

    public Response getAll(User user){
        Response response = new Response();

        try{
            List<app.entity.order.Order> orders;
            if(user.getRole() != UserRole.ADMINISTRATOR.getCode()) {
                orders = _orderRepository.getAll(user.getId());
            }
            else {
               orders = _orderRepository.getAll();
            }

            if (CollectionUtils.isEmpty(orders)){
                response.data = new ArrayList<>();
                response.addSuccess("No hay pedidos.");
                return response;
            }

            response.data = orders;
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un error al obtener los pedidos.");
            return response;
        }
    }

    public Response getById(long id, User user){
        Response response = new Response();
        Order orderModel = new Order();
        try {

            // First get order
            app.entity.order.Order order = _orderRepository.getById(id);

            if(order.getUserId().getId() != user.getId() || user.getRole() != UserRole.ADMINISTRATOR.getCode()) {
                response.addError("Pedido no encontrado");
                return response;
            }

            if (order == null) {
                response.addError(String.format("No hay pedido con id: %s",id));
                return response;
            }

            // Then get items
            List<app.entity.order.Item> items = _itemRepository.getByOrderId(order);

            Float total = 0.0f;
            for(app.entity.order.Item item: items) {
                total += item.getPrecio();
            }

            orderModel.setItems(items);
            orderModel.setCliente(user);
            orderModel.setIdPedido(id);
            orderModel.setTotal(total);
            orderModel.setPagado(order.getPagado());
            orderModel.setEstado(order.getStatus());

            // Return model instead of entities

            response.data = orderModel;
            response.addSuccess("Se encontró el pedido deseado");
            return response;

        }catch (Exception e){
            response.addError("Ocurrió un problema al buscar el pedido");
            return response;
        }
    }

    public Response delete(long id){
        Response response = new Response();
        try{
            _orderRepository.delete(id);
            response.addSuccess("Se removió el pedido");
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un problema al eliminar el pedido");
            return response;
        }
    }

    public Response update(Long pedidoId, User user, Order order) {

        app.entity.order.Order orderEntity = _orderRepository.getById(pedidoId);
        orderEntity.setStatus(order.getEstado());
        orderEntity.setPagado(order.getPagado());

        orderEntity = _orderRepository.save(orderEntity);

        Response response = new Response();
        response.addSuccess("Pedido modificado con éxito.");
        response.data = orderEntity;
        return response;
    }
}
