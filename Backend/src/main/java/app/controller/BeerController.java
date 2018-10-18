package app.controller;

import app.infraestructure.Message;
import app.infraestructure.MessageType;
import app.infraestructure.Response;
import app.model.BeerModel;
import app.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beer")
@CrossOrigin(origins = "*")
public class BeerController {

    @Autowired
    BeerService _service;

    @RequestMapping("/dummy")
    public String dummy()
    {
        return "Beer!";
    }

    @PostMapping("/create")
    public Response<BeerModel> create(@RequestBody BeerModel beer)
    {
        Response<BeerModel> response = new Response<BeerModel>();

        try {

            _service.Create(beer);

            response.messages.add(new Message("Beer succesfully created.", MessageType.Success));
        }
        catch (Exception ex) {
            response.messages.add(new Message("Error: " + ex.getMessage(), MessageType.Error));
        }

        return response;
    }

    @RequestMapping("/update")
    public String update()
    {
        return "Up and running!";
    }

    @RequestMapping("/delete")
    public String delete()
    {
        return "Up and running!";
    }
}
