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
@CrossOrigin(origins = "*")
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

    @DeleteMapping(value = "/{user}")
    @ResponseBody
    public ResponseEntity<Response> deleteUser(@PathVariable("user") long userId){
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

    @GetMapping(value = "/{user}")
    @ResponseBody
    public ResponseEntity<Response> getById(@PathVariable("user") long userId){
        Response response = userService.getById(userId);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
