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
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/client/create")
    @ResponseBody
    public ResponseEntity<Response> createClient(@RequestBody ClientModel newClient){
        Response response = clientService.createClient(newClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/client/delete")
    @ResponseBody
    public ResponseEntity<Response> deleteClient(@RequestBody ClientModel newClient){
        Response response = clientService.deleteClient(newClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/client/update")
    @ResponseBody
    public ResponseEntity<Response> updateClient(@RequestBody ClientModel newClient){
        Response response = clientService.updateClient(newClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client/clients")
    @ResponseBody
    public ResponseEntity<Response> getClients(){
        Response response = clientService.getAllClients();

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client")
    @ResponseBody
    public ResponseEntity<Response> getById(@RequestParam("clientId") long idClient){
        Response response = clientService.getById(idClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client-by-cuit")
    @ResponseBody
    public ResponseEntity<Response> getByCuit(@RequestParam("cuit") long cuit){
        Response response = clientService.getByCuit(cuit);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
