package app.controller;

import app.infraestructure.Response;
import app.model.BeerModel;
import app.model.BeerStockModel;
import app.service.BeerService;
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

    @PostMapping("/beer")
    @ResponseBody
    public ResponseEntity<Response> post(@RequestBody BeerModel newBeer) {
        Response<BeerModel> response = _service.create(newBeer);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/beer/{beer}")
    @ResponseBody
    public ResponseEntity<Response> delete(@PathVariable("beer") long beerId){
        Response response = _service.delete(beerId);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/beer")
    @ResponseBody
    public ResponseEntity<Response> put(@RequestBody BeerModel newBeer){
        Response response = _service.update(newBeer);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/beer/stock")
    @ResponseBody
    public ResponseEntity<Response> stock(@RequestBody BeerStockModel model){
        Response response = _service.addStock(model);

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

    @GetMapping(value = "/beer/{beer}")
    @ResponseBody
    public ResponseEntity<Response> getById(@PathVariable("beer") long idClient){
        Response response = _service.getById(idClient);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/beer/name/{name}")
    @ResponseBody
    public ResponseEntity<Response> getByName(@PathVariable("name") String name){
        Response response = _service.getByName(name);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
