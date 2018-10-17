package app.service;

import app.entity.Client;
import app.infraestructure.Response;
import app.model.ClientModel;
import app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Response createClient(ClientModel model){

        Response response = new Response();

        if (model.getId() != null && clientRepository.exists(model.getId())){
            response.addError("El cliente ya existe");
            return response;
        }

        Client client = new Client();
        client.setAddress(model.getAddress());
        client.setBusinessName(model.getBusinessName());
        client.setCuit(model.getCuit());
        client.setEmail(model.getEmail());
        client.setPhoneNumber(model.getPhoneNumber());

        try{
            response.data = clientRepository.save(client);
            response.addSuccess("Cliente creado satisfactoriamente");
        } catch (Exception e){
            response.addError("Ocurrió un error al crear el cliente");
        }
        return response;
    }

    public Response getAllClients(){
        Response response = new Response();

        try{
            List<Client> clients = clientRepository.getAll();

            if (CollectionUtils.isEmpty(clients)){
                response.data = new ArrayList<>();
                response.addSuccess("No hay clientes");
                return response;
            }

            response.data = clients;
            response.addSuccess(String.format("Se devolvió una lista de %s clientes",clients.size()));
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un error al obtener los clientes");
            return response;
        }
    }

    public Response getById(long id){
        Response response = new Response();
        try {
            Client client = clientRepository.getById(id);

            if (client == null) {
                response.addError(String.format("No hay clientes con id: %s",id));
                return response;
            }
            response.data = client;
            response.addSuccess("Se encontró el cliente deseado");
            return response;

        }catch (Exception e){
            response.addError("Ocurrió un problema al buscar el cliente");
            return response;
        }
    }

    public Response getByCuit(long cuit){
        Response response = new Response();
        try {
            Client client = clientRepository.getByCuit(cuit);

            if (client == null) {
                response.addError(String.format("No hay clientes con cuit: %s",cuit));
                return response;
            }
            response.data = client;
            response.addSuccess("Se encontró el cliente deseado");
            return response;

        }catch (Exception e){
            response.addError("Ocurrió un problema al buscar el cliente");
            return response;
        }
    }

    public Response deleteClient(ClientModel model){
        Response response = new Response();
        try{
            Client client = new Client();
            client.setAddress(model.getAddress());
            client.setBusinessName(model.getBusinessName());
            client.setCuit(model.getCuit());
            client.setEmail(model.getEmail());
            client.setPhoneNumber(model.getPhoneNumber());

            clientRepository.delete(client);
            response.addSuccess("Se removió el cliente");
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un problema al eliminar el cliente");
            return response;
        }

    }

    public Response updateClient(ClientModel model){
        Response response = new Response();
        try {

            Client oldClient = clientRepository.getById(model.getId());

            if (oldClient != null) {
                if (!oldClient.getCuit().equals(model.getCuit()) && clientRepository.getByCuit(model.getCuit()) != null){
                    response.addError("Ya existe el cuit");
                    return response;
                }else{

                    Client newClient = new Client();
                    newClient.setClientId(oldClient.getClientId());
                    newClient.setAddress(model.getAddress());
                    newClient.setBusinessName(model.getBusinessName());
                    newClient.setCuit(model.getCuit());
                    newClient.setEmail(model.getEmail());
                    newClient.setPhoneNumber(model.getPhoneNumber());
                    clientRepository.update(newClient);
                    response.addSuccess("Cliente actualizado satisfactoriamente");
                    return response;
                }
            }else{
                response.addError("El cliente no existe");
                return response;
            }

        }catch (Exception e ){
            response.addError("Ocurrió un problema al actualizar el cliente");
            return response;
        }
    }
}
