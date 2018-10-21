package app.service;

import app.entity.Beer;
import app.entity.Container;
import app.entity.order.OrderState;
import app.entity.user.User;
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


        // First create order
        app.entity.order.Order order = new app.entity.order.Order();
        order.setDate(new Date());
        order.setUserId(user);
        order.setVisible(true);
        order.setStatus(OrderState.PENDING.getCode());

        order = _orderRepository.save(order);

        List<app.entity.order.Item> items = new ArrayList<>();
        Float total = 0.0f;

        // Then create items and assign them to the order
        for(Item itemModel: itemsModels) {
            app.entity.order.Item itemEntity = new app.entity.order.Item();

            Beer beer = _beerRepository.getById(itemModel.getIdCerveza());
            itemEntity.setBeerId(beer);

            Container container = _containerRepository.getById(itemModel.getIdContenedor());
            itemEntity.setContainerId(container);

            itemEntity.setCantidad(itemModel.getCantidad());

            itemEntity.setOrderId(order);

            itemEntity = _itemRepository.save(itemEntity);

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

        /*if (model.getId() != null && _orderRepository.exists(model.getId())){
            response.addError("El cerveza ya existe");
            return response;
        }

        app.entity.order.Order order = new app.entity.order.Order();
        order.setAmount(model.getAmount());
        order.setDate(model.getDate());
        order.setStatus(model.getStatus());
        order.setUserId(model.getUserId());
        order.setVisible(model.getVisible());

        try{
            response.data = _orderRepository.save(order);
            response.addSuccess("Orden creada satisfactoriamente.");
        } catch (Exception e){
            response.addError("Ocurrió un error al intentar crear la orden.");
        }
        return response;*/
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

    public Response getById(long id){
        Response response = new Response();
        try {
            app.entity.order.Order order = _orderRepository.getById(id);

            if (order == null) {
                response.addError(String.format("No hay pedido con id: %s",id));
                return response;
            }
            response.data = order;
            response.addSuccess("Se encontró la cerveza deseada");
            return response;

        }catch (Exception e){
            response.addError("Ocurrió un problema al buscar la cerveza");
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
}
