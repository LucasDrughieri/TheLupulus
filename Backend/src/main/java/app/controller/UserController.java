package app.controller;

import app.infraestructure.Response;
import app.model.user.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //Add new user
    @GetMapping("/user/{id}")
    public Response<User> get(@PathVariable("id") long id){
        User user = userService.get(id);
        Response prueba = new Response();
        prueba.data = user;
        prueba.messages.add("Probando")
        return prueba;
    }
}
