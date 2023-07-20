package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.get.UserOrderDTO;
import com.example.demo.dto.get.UserOrderMapper;
import com.example.demo.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @NonNull
    private final UserService userService;
    @NonNull
    private final UserOrderMapper userOrderMapper;

/*
    public UserController(UserService userService) {
        this.userService = userService;
    }
*/

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @GetMapping
    @ResponseBody
    public List<UserOrderDTO> getUserOrder() {
        return userService.getAllUsers()
                .stream()
                .map(UserOrderMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        updatedUser.setId(id);
        return userService.updateUser(updatedUser);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}

