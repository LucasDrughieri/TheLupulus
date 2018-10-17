package app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DummyController {

    @RequestMapping("/dummy")
    public String hello(){
        return "Up and running!";
    }
}
