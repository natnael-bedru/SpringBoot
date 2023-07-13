package com.example.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")

public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

//    @PostMapping
//    public Order createOrder(@RequestBody Order order)
//    {
//        return orderService.createOrder(order);
//    }
@PostMapping(path = "{user_id}")
public ResponseEntity<Order> createOrder(@PathVariable("user_id") Long id, @RequestBody Order newOrder) {
    User user = userService.getUserById(id);
    if (user == null) {
        return ResponseEntity.notFound().build(); // Return 404 Not Found status
    } else {
        newOrder.setDate(newOrder.getDate());
        newOrder.set_user(user);
        Order createdOrder = orderService.createOrder(newOrder);
        return ResponseEntity.ok(createdOrder); // Return 200 OK status with the created Order object
    }
}




    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable("id") Long id, @RequestBody Order updatedOrder) {
        updatedOrder.setId(id);
        return orderService.updateOrder(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
    }
}

