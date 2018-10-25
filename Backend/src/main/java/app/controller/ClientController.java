package app.controller;

import app.entity.UserSession;
import app.entity.user.User;
import app.entity.user.UserRole;
import app.infraestructure.Response;
import app.model.ClientModel;
import app.repository.UserRepository;
import app.repository.UserSessionRepository;
import app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    UserSessionRepository sessionRepository;

    @Autowired
    UserRepository _userRepository;

    @PostMapping(value = "/client")
    @ResponseBody
    public ResponseEntity<Response> createClient(@RequestBody ClientModel newClient ,@RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = sessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = clientService.createClient(newClient);
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/client/{clientId}")
    @ResponseBody
    public ResponseEntity<Response> deleteClient(@PathVariable("clientId") long clientId,@RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = sessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = clientService.deleteClient(clientId);
        } else response.addError("El usuario no es administrador");


        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/client")
    @ResponseBody
    public ResponseEntity<Response> updateClient(@RequestBody ClientModel newClient,@RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = sessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = clientService.updateClient(newClient);
        } else response.addError("El usuario no es administrador");


        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client")
    @ResponseBody
    public ResponseEntity<Response> getClients(@RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = sessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = clientService.getAllClients();
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client/{clientId}")
    @ResponseBody
    public ResponseEntity<Response> getById(@PathVariable("clientId") long idClient,@RequestHeader(value = "Authorization") String sessionToken){
        Response response = new Response();

        UserSession session = sessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {

            response = clientService.getClientById(idClient);

        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client-by-cuit")
    @ResponseBody
    public ResponseEntity<Response> getByCuit(@RequestParam("cuit") long cuit,@RequestHeader(value = "Authorization") String sessionToken){


        Response response = new Response();

        UserSession session = sessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {

            response = clientService.getByCuit(cuit);

        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
