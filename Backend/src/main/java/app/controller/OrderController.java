package app.controller;

import app.entity.UserSession;
import app.entity.user.User;
import app.entity.user.UserRole;
import app.infraestructure.Response;
import app.model.order.Item;
import app.model.order.ItemList;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    public ResponseEntity<Response> post(@RequestBody ItemList items, @RequestHeader(value = "Authorization") String sessionToken) {
        if (!UserSession.validateAccess(_userSessionRepository, sessionToken, new Integer[] { UserRole.NORMAL_USER.getCode() } )) {
            return UserSession.errorResponse();
        }

        // Get user logged in
        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        // Create order
        Response<Order> response = _orderService.create(items.getItems(),user);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pedido")
    @ResponseBody
    public ResponseEntity<Response> getall(@RequestHeader(value = "Authorization") String sessionToken) {
        if (!UserSession.validateAccess(_userSessionRepository, sessionToken, new Integer[] { UserRole.ADMINISTRATOR.getCode(), UserRole.NORMAL_USER.getCode() } )) {
            return UserSession.errorResponse();
        }

        // Get user logged in
        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        // Create order
        Response<Order> response = _orderService.getAll(user);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pedido/{pedidoId}")
    @ResponseBody
    public ResponseEntity<Response> get(@PathVariable("pedidoId") Long pedidoId, @RequestHeader(value = "Authorization") String sessionToken) {
        if (!UserSession.validateAccess(_userSessionRepository, sessionToken, new Integer[] { UserRole.ADMINISTRATOR.getCode(), UserRole.NORMAL_USER.getCode() } )) {
            return UserSession.errorResponse();
        }

        // Get user logged in
        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        // Create order
        Response<Order> response = _orderService.getById(pedidoId, user);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/pedido/{pedidoId}")
    @ResponseBody
    public ResponseEntity<Response> patch(@RequestBody Order order, @PathVariable("pedidoId") Long pedidoId, @RequestHeader(value = "Authorization") String sessionToken) {
        if (!UserSession.validateAccess(_userSessionRepository, sessionToken, new Integer[] { UserRole.ADMINISTRATOR.getCode() } )) {
            return UserSession.errorResponse();
        }

        // Get user logged in
        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        // Create order
        Response<Order> response = _orderService.update(pedidoId, user, order);

        if(response.hasErrors()) return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
