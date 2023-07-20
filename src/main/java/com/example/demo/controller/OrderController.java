package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.dto.get.UserOrderMapper;
import com.example.demo.dto.get.OrderDTO;
import com.example.demo.dto.post.UserOrderCreationDTO;
import com.example.demo.dto.post.ResponceDTO;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @NonNull
    private final OrderService orderService;
    @NonNull
    private final UserService userService;

//    @PostMapping
//    public Order createOrder(@RequestBody Order order)
//    {
//        return orderService.createOrder(order);
//    }
//@PostMapping(path = "/{user_id}")
//public ResponseEntity<Order> createOrder(@PathVariable("user_id") Long id, @RequestBody Order newOrder) {
//    User user = userService.getUserById(id);
//    String test;
//    if (user == null) {
//        return ResponseEntity.notFound().build(); // Return 404 Not Found status
//    } else {
//        newOrder.setDate(LocalDate.now());
//        newOrder.set_user(user);
//        Order createdOrder = orderService.createOrder(newOrder);
//        return ResponseEntity.ok(createdOrder); // Return 200 OK status with the created Order object
//    }
//}
@PostMapping()
public ResponseEntity<ResponceDTO> createOrder(@RequestBody UserOrderCreationDTO newOrder) {
    Long userId = userService.findIdByUserName(newOrder.getUserName());
   if(userId != null) {
       List<String> unOrderedProducts = new ArrayList<>();
       for (String productName : newOrder.getProductName()) {
           Optional<List<Long>> userList = orderService.findUserIdsByProductName(productName);
           // if there is a product in the order but not assigned to this user
           userList.ifPresent(userListData -> {
               for (Long userIdData : userListData) {
                  if(userIdData != userId){
                    unOrderedProducts.add(productName);
                  }
               }
           });
           // if the product is new to the order table
           if(userList.get().isEmpty() && !productName.isEmpty()){
               unOrderedProducts.add(productName);
           }

       }
       if(!unOrderedProducts.isEmpty()){
           unOrderedProducts.forEach(productName -> orderService.createOrder(new Order(productName,LocalDate.now(),userService.getUserById(userId))));
           return new ResponseEntity<ResponceDTO>(new ResponceDTO("New products added!",String.join(", ", unOrderedProducts)), HttpStatus.OK);
       }
       else{
           return new ResponseEntity<ResponceDTO>(new ResponceDTO("All products exist. No changes applied!",String.join(", ", newOrder.getProductName())), HttpStatus.OK);
       }
   }else {
       return new ResponseEntity<ResponceDTO>(new ResponceDTO("User Doesn't Exist",newOrder.getUserName()), HttpStatus.NOT_FOUND);
   }
}


    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(UserOrderMapper::toDto)
                .collect(toList());
       // return orderService.getAllOrders();
    }

//    @GetMapping("/{id}")
//    public Order getOrderById(@PathVariable("id") Long id) {
//        return orderService.getOrderById(id);
//    }

    @GetMapping("/{user_id}")
    public List<OrderDTO> findProductNamesByUserId(@PathVariable("user_id") Long user_id) {

        return orderService.findProductNamesBy_userId(user_id).orElse(List.of())
                .stream()
                .map(UserOrderMapper::toDto)
                .collect(toList());
    }

    //findProductNamesByUserName
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

