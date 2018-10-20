package app.service;

import app.entity.Container;
import app.infraestructure.Response;
import app.model.ContainerModel;
import app.model.ContainerStockModel;
import app.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContainerService {

    @Autowired
    private ContainerRepository containerRepository;

    public Response create(ContainerModel model){

        Response response = new Response();

        Container container = new Container();
        container.setName(model.getName());
        container.setHeight(model.getHeight());
        container.setWidth(model.getWidth());
        container.setCapacity(model.getCapacity());
        container.setMaterial(model.getMaterial());
        container.setQuantity(model.getQuantity());

        try{
            response.data = containerRepository.save(container);
            response.addSuccess("Contenedor creado satisfactoriamente");
        } catch (Exception e){
            response.addError("Ocurrió un error al crear el contenedor");
        }
        return response;
    }

    public Response getAll(){
        Response response = new Response();

        try{
            List<Container> containers = containerRepository.getAll();

            if (CollectionUtils.isEmpty(containers)){
                response.data = new ArrayList<>();
                response.addSuccess("No hay contenedores");
                return response;
            }

            response.data = containers;
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un error al obtener los contenedores");
            return response;
        }
    }

    public Response getById(long id){
        Response response = new Response();
        try {
            Container container = containerRepository.getById(id);

            if (container == null) {
                response.addError(String.format("No hay contenedores con id: %s",id));
                return response;
            }
            response.data = container;
            response.addSuccess("Se encontró el contenedor deseado");
            return response;

        }catch (Exception e){
            response.addError("Ocurrió un problema al buscar el contenedor");
            return response;
        }
    }

    public Response delete(long id){
        Response response = new Response();
        try{
            containerRepository.delete(id);
            response.addSuccess("Se removió el contenedor");
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un problema al eliminar el contenedor");
            return response;
        }
    }

    public Response update(ContainerModel model){
        Response response = new Response();
        try {

            Container oldContainer = containerRepository.getById(model.getId());

            if (oldContainer != null) {
                    Container newContainer = new Container();
                    newContainer.setId(oldContainer.getId());
                    newContainer.setName(model.getName());
                    newContainer.setHeight(model.getHeight());
                    newContainer.setWidth(model.getWidth());
                    newContainer.setCapacity(model.getCapacity());
                    newContainer.setMaterial(model.getMaterial());
                    newContainer.setQuantity(model.getQuantity());

                    containerRepository.update(newContainer);
                    response.addSuccess("Contenedor actualizado satisfactoriamente");
                    return response;
            }
            else{
                response.addError("El contenedor no existe");
                return response;
            }

        }catch (Exception e ){
            response.addError("Ocurrió un problema al actualizar el contenedor");
            return response;
        }
    }

    public Response addStock(ContainerStockModel model) {
        Response response = new Response();

        try {
            Container beer = containerRepository.getById(model.id);

            if (beer != null) {
                beer.addStock(model.quantity);
                containerRepository.update(beer);

                response.addSuccess("Stock actualizado correctamente");
                return response;
            }
            else{
                response.addError("El contenedor no existe");
                return response;
            }
        }
        catch (Exception e ){
            response.addError("Ocurrió un problema al actualizar la cerveza");
            return response;
        }
    }
}
