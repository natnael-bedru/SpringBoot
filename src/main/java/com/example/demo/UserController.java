package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }
    @PostMapping
    public ResponseEntity createUser(@JsonArg("name") String user_name) {
        User user = new User(user_name);
        userService.createUser(user);
        return ResponseEntity.ok().body(userService.createUser(user));
        //return ResponseEntity.ok().body(String.format(String.format("{\"User name \": %s", user_name)));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
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

