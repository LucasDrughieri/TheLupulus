package app.service;

import app.entity.UserSession;
import app.entity.user.User;
import app.infraestructure.Response;
import app.model.UserSessionModel;
import app.repository.UserRepository;
import app.repository.UserSessionRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserSessionService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private UserSessionRepository _sessionRepository;

    public Response create(UserSessionModel model){

        Response response = new Response();

        User user = _userRepository.getByNickname(model.getUser());

        if(user == null){
            response.addError("Usuario inexistente.");
            return response;
        }

        if(user != null && !user.getPassword().equals(model.getPassword())){
            response.addError("Usuario o contraseña incorrecta");
            return response;
        }

        UserSession session = new UserSession();
        session.setUserId(user);
        session.setToken(UUID.randomUUID().toString());
        session.setLastLogin(DateTime.now().toDate());

        try{
            response.data = _sessionRepository.save(session);
            response.addSuccess("Sesión de usuario creada satisfactoriamente");
        } catch (Exception e){
            response.addError("Ocurrió un error al crear sesión de usuario");
        }
        return response;
    }
}
