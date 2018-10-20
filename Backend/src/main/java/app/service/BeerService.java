package app.service;

import app.entity.Beer;
import app.infraestructure.Response;
import app.model.BeerModel;
import app.model.BeerStockModel;
import app.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService {

    @Autowired
    BeerRepository _repository;

    public Response create(BeerModel model){

        Response response = new Response();

        if (model.getId() != null && _repository.exists(model.getId())){
            response.addError("El cerveza ya existe");
            return response;
        }

        Beer beer = new Beer();
        beer.setColor(model.getColor());
        beer.setDensity(model.getDensity());
        beer.setGraduation(model.getGraduation());
        beer.setGranos(model.getGranos());
        beer.setIbu(model.getIbu());
        beer.setName(model.getName());
        beer.setPricePerLitre(model.getPricePerLitre());
        beer.setQuantity(model.getQuantity());
        beer.setVisible(model.getVisible());

        try{
            response.data = _repository.save(beer);
            response.addSuccess("Cerveza creada satisfactoriamente.");
        } catch (Exception e){
            response.addError("Ocurrió un error al intentar crear la cerveza.");
        }
        return response;
    }

    public Response getAll(){
        Response response = new Response();

        try{
            List<Beer> beers = _repository.getAll();

            if (CollectionUtils.isEmpty(beers)){
                response.data = new ArrayList<>();
                response.addSuccess("No hay cervezas.");
                return response;
            }

            response.data = beers;
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un error al obtener las cervezas");
            return response;
        }
    }

    public Response getById(long id){
        Response response = new Response();
        try {
            Beer beer = _repository.getById(id);

            if (beer == null) {
                response.addError(String.format("No hay cerveza con id: %s",id));
                return response;
            }
            response.data = beer;
            response.addSuccess("Se encontró la cerveza deseada");
            return response;

        }catch (Exception e){
            response.addError("Ocurrió un problema al buscar la cerveza");
            return response;
        }
    }

    public Response getByName(String name){
        Response response = new Response();
        try {
            Beer beer = _repository.getByName(name);

            if (beer == null) {
                response.addError(String.format("No hay cerveza con nombre: %s",name));
                return response;
            }
            response.data = beer;
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
            _repository.delete(id);
            response.addSuccess("Se removió la cerveza");
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un problema al eliminar la cerveza");
            return response;
        }
    }

    public Response update(BeerModel model){
        Response response = new Response();
        try {

            Beer oldBeer = _repository.getById(model.getId());

            if (oldBeer != null) {
                //if (!oldBeer.getName().equals(model.getName()) && _repository.getByName(model.getName()) != null){
                //    response.addError("Ya existe el nombre");
                //    return response;
                //}else{

                    Beer newBeer = new Beer();
                    newBeer.setBeerId(model.getId());
                    newBeer.setVisible(model.getVisible());
                    newBeer.setQuantity(model.getQuantity());
                    newBeer.setPricePerLitre(model.getPricePerLitre());
                    newBeer.setName(model.getName());
                    newBeer.setIbu(model.getIbu());
                    newBeer.setGranos(model.getGranos());
                    newBeer.setGraduation(model.getGraduation());
                    newBeer.setColor(model.getColor());
                    newBeer.setDensity(model.getDensity());

                    _repository.update(newBeer);
                    response.addSuccess("Cerveza actualizada satisfactoriamente");
                    return response;
            }
            else{
                response.addError("La cerveza no existe");
                return response;
            }

        }catch (Exception e ){
            response.addError("Ocurrió un problema al actualizar la cerveza");
            return response;
        }
    }

    public Response addStock(BeerStockModel model) {
        Response response = new Response();

        try {
            Beer beer = _repository.getById(model.id);

            if (beer != null) {
                beer.addStock(model.quantity);
                _repository.update(beer);

                response.addSuccess("Stock actualizado correctamente");
                return response;
            }
            else{
                response.addError("La cerveza no existe");
                return response;
            }
        }
        catch (Exception e ){
            response.addError("Ocurrió un problema al actualizar la cerveza");
            return response;
        }
    }
}
