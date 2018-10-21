package app.controller;

import app.entity.UserSession;
import app.entity.user.User;
import app.infraestructure.Response;
import app.model.order.Item;
import app.model.order.Order;
import app.repository.UserRepository;
import app.repository.UserSessionRepository;
import app.service.OrderService;
import app.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    OrderService _orderService;

    @Autowired
    UserSessionRepository _userSessionRepository;

    @Autowired
    UserRepository _userRepository;

    @PostMapping("/pedido")
    @ResponseBody
    public ResponseEntity<Response> post(@RequestBody List<Item> items, @RequestHeader(value = "X-AuthToken") String sessionToken) {

        // Get user logged in
        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        // Create order
        Response<Order> response = _orderService.create(items,user);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
