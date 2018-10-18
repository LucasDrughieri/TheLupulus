package app.controller;

import app.infraestructure.Response;
import app.model.ClientModel;
import app.model.user.UserModel;
import app.service.ClientService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Response> createUser(@RequestBody UserModel newUser){
        Response response = userService.createUser(newUser);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    @ResponseBody
    public ResponseEntity<Response> deleteUser(@PathVariable("userId") long userId){
        Response response = userService.deleteUser(userId);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Response> getUsers(){
        Response response = userService.getAllUsers();

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    @ResponseBody
    public ResponseEntity<Response> getById(@PathVariable("userId") long userId){
        Response response = userService.getById(userId);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
