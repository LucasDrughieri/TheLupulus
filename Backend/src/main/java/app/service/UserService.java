package app.service;


import app.entity.user.User;
import app.infraestructure.Response;
import app.model.user.UserModel;
import app.model.user.UserRole;
import app.repository.ClientRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Response createUser(UserModel model){

        Response response = new Response();

        if (model.getId() != null && userRepository.exists(model.getId())){
            response.addError("El usuario ya existe");
            return response;
        }

        if (model.getClientId() != null  && !clientRepository.exists(model.getClientId())){
            response.addError("El cliente asociado no existe");
            return response;
        }

        User user = new User();
        user.setClientId(clientRepository.getById(model.getClientId()));
        user.setNickname(model.getNickname());
        user.setPassword(model.getPassword());
        UserRole userRole = UserRole.getRole(model.getRole());
        if (userRole == null){
            response.addError("No es un codigo de rol de usuario disponible");
            return response;
        }
        user.setRole(userRole.getCode());

        try{
            response.data = userRepository.save(user);
            response.addSuccess("Usuario creado satisfactoriamente");
        } catch (Exception e){
            response.addError("Ocurrió un error al crear el usuario");
        }
        return response;
    }

    public Response getAllUsers(){
        Response response = new Response();

        try{
            List<User> users = userRepository.getAll();

            if (CollectionUtils.isEmpty(users)){
                response.data = new ArrayList<>();
                response.addSuccess("No hay usuarios");
                return response;
            }

            response.data = users;
            response.addSuccess(String.format("Se devolvió una lista de %s usuarios",users.size()));
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un error al obtener los usuarios");
            return response;
        }
    }

    public Response getById(long id){
        Response response = new Response();
        try {
            User user = userRepository.getById(id);

            if (user == null) {
                response.addError(String.format("No hay usuario con id: %s",id));
                return response;
            }
            response.data = user;
            response.addSuccess("Se encontró el usuario deseado");
            return response;

        }catch (Exception e){
            response.addError("Ocurrió un problema al buscar el usuario");
            return response;
        }
    }

    public Response deleteUser(long id){
        Response response = new Response();
        try{
            userRepository.delete(id);
            response.addSuccess("Se removió el usuario");
            return response;
        }catch (Exception e){
            response.addError("Ocurrió un problema al eliminar el usuario");
            return response;
        }

    }
}
