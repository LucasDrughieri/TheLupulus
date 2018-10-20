package app.controller;

import app.infraestructure.Response;
import app.model.BeerModel;
import app.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BeerController {

    @Autowired
    BeerService _service;

    @PostMapping("/beer")
    @ResponseBody
    public ResponseEntity<Response> createBeer(@RequestBody BeerModel newBeer) {
        Response<BeerModel> response = _service.create(newBeer);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/beer/{beerId}")
    @ResponseBody
    public ResponseEntity<Response> deleteClient(@PathVariable("beerId") long beerId){
        Response response = _service.delete(beerId);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/beer")
    @ResponseBody
    public ResponseEntity<Response> updateClient(@RequestBody BeerModel newBeer){
        Response response = _service.update(newBeer);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/beer")
    @ResponseBody
    public ResponseEntity<Response> getAll(){
        Response response = _service.getAll();

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/beer/{beerId}")
    @ResponseBody
    public ResponseEntity<Response> getById(@PathVariable("beerId") long idClient){
        Response response = _service.getById(idClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/beer/name/{name}")
    @ResponseBody
    public ResponseEntity<Response> getByName(@RequestParam("name") String name){
        Response response = _service.getByName(name);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
