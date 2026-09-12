package com.linkedlite.backend.controller;

import com.linkedlite.backend.dto.LoginRequest;
import com.linkedlite.backend.entity.User;
import com.linkedlite.backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.registerUser(user);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String fullName) {

        return userService.searchUsers(fullName);

    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {

        return userService.getUserById(id);

    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id,
                           @RequestBody User user) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userService.updateUser(id, user,email);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        String loggedInEmail=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        userService.deleteUser(id, loggedInEmail);
        return "User deleted successfully";
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
    }
}
