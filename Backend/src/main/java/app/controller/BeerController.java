package app.controller;

import app.entity.UserSession;
import app.entity.user.User;
import app.entity.user.UserRole;
import app.infraestructure.Response;
import app.model.BeerModel;
import app.model.BeerStockModel;
import app.repository.UserRepository;
import app.repository.UserSessionRepository;
import app.service.BeerService;
import app.service.UserService;
import app.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BeerController {

    @Autowired
    BeerService _service;

    @Autowired
    UserSessionRepository _userSessionRepository;

    @Autowired
    UserRepository _userRepository;

    @PostMapping("/beer")
    @ResponseBody
    public ResponseEntity<Response> post(@RequestBody BeerModel newBeer,  @RequestHeader(value = "Authorization") String sessionToken) {

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = _service.create(newBeer);
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/beer/{beer}")
    @ResponseBody
    public ResponseEntity<Response> delete(@PathVariable("beer") long beerId,  @RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = _service.delete(beerId);
        } else response.addError("El usuario no es administrador");


        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/beer")
    @ResponseBody
    public ResponseEntity<Response> put(@RequestBody BeerModel newBeer,  @RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = _service.update(newBeer);
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/beer/stock")
    @ResponseBody
    public ResponseEntity<Response> stock(@RequestBody BeerStockModel model,  @RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = _service.addStock(model);
        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/beer")
    @ResponseBody
    public ResponseEntity<Response> getAll( @RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response =_service.getAll();

        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/beer/{beer}")
    @ResponseBody
    public ResponseEntity<Response> getById(@PathVariable("beer") long idClient,  @RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = _service.getById(idClient);

        } else response.addError("El usuario no es administrador");


        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/beer/name/{name}")
    @ResponseBody
    public ResponseEntity<Response> getByName(@PathVariable("name") String name,  @RequestHeader(value = "Authorization") String sessionToken){

        Response response = new Response();

        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user != null && user.getRole().equals(UserRole.ADMINISTRATOR.getCode())) {
            response = response = _service.getByName(name);

        } else response.addError("El usuario no es administrador");

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
