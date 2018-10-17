package app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @RequestMapping("/health-check")
    public String healthCheck(){
        return "Up and running!";
    }
}
