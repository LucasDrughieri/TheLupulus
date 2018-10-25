package app.controller;

import app.infraestructure.Response;
import app.model.ContainerModel;
import app.model.ContainerStockModel;
import app.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/container")
@CrossOrigin(origins = "*")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Response> post(@RequestBody ContainerModel model,@RequestHeader(value = "Authorization") String sessionToken){
        Response response = containerService.create(model);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "{container}")
    @ResponseBody
    public ResponseEntity<Response> delete(@PathVariable("container") long containerId, @RequestHeader(value = "Authorization") String sessionToken){
        Response response = containerService.delete(containerId);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<Response> put(@RequestBody ContainerModel model,@RequestHeader(value = "Authorization") String sessionToken){
        Response response = containerService.update(model);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/stock")
    @ResponseBody
    public ResponseEntity<Response> stock(@RequestBody ContainerStockModel model,@RequestHeader(value = "Authorization") String sessionToken){
        Response response = containerService.addStock(model);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Response> get(@RequestHeader(value = "Authorization") String sessionToken){
        Response response = containerService.getAll();

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "{container}")
    @ResponseBody
    public ResponseEntity<Response> getById(@PathVariable("container") long containerId, @RequestHeader(value = "Authorization") String sessionToken){
        Response response = containerService.getById(containerId);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
