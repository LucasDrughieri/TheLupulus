package app.controller;

import app.entity.UserSession;
import app.infraestructure.Response;
import app.model.UserSessionModel;
import app.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserSessionController {

    @Autowired
    UserSessionService _service;

    @PostMapping("/session/create")
    @ResponseBody
    public ResponseEntity<Response> create(@RequestBody UserSessionModel session) {
        Response response = _service.create(session);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/session/destroy")
    @ResponseBody
    public ResponseEntity<Response> destroy(@RequestParam("user") String user, @RequestParam("token") String token){
        Response response = new Response();

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
