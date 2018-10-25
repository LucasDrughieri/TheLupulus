package app.controller;

import app.entity.UserSession;
import app.entity.user.User;
import app.entity.user.UserRole;
import app.infraestructure.Response;
import app.model.ClientModel;
import app.model.user.UserModel;
import app.repository.UserSessionRepository;
import app.service.ClientService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    UserSessionRepository _userSessionRepository;


    @PostMapping
    @ResponseBody
    public ResponseEntity<Response> createUser(@RequestBody UserModel newUser, @RequestHeader(value = "Authorization") String sessionToken){
        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = userService.createUser(newUser);
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{user}")
    @ResponseBody
    public ResponseEntity<Response> deleteUser(@PathVariable("user") long userId, @RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = userService.deleteUser(userId);
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Response> getUsers(@RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = userService.getAllUsers();
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{user}")
    @ResponseBody
    public ResponseEntity<Response> getById(@PathVariable("user") long userId, @RequestHeader(value = "Authorization") String sessionToken){
        Response response = userService.getById(userId);

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = userService.getById(userId);
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
