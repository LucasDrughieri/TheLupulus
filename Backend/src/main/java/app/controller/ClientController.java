package app.controller;

import app.infraestructure.Response;
import app.model.ClientModel;
import app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/client")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<Response> createClient(@RequestBody ClientModel newClient){
        Response response = clientService.createClient(newClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<Response> deleteClient(@RequestBody ClientModel newClient){
        Response response = clientService.deleteClient(newClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<Response> updateClient(@RequestBody ClientModel newClient){
        Response response = clientService.updateClient(newClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/clients")
    @ResponseBody
    public ResponseEntity<Response> getClients(@RequestBody ClientModel newClient){
        Response response = clientService.getAllClients();

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client")
    @ResponseBody
    public ResponseEntity<Response> getById(@RequestParam long idClient){
        Response response = clientService.getById(idClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client")
    @ResponseBody
    public ResponseEntity<Response> getByCuit(@RequestParam long cuit){
        Response response = clientService.getByCuit(cuit);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
