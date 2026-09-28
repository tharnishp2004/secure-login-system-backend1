package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE USER
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User savedUser = userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User userDetails) {

        return userRepository.findById(id)
                .map(user -> {

                    user.setEmail(userDetails.getEmail());
                    user.setName(userDetails.getName());
                    user.setPassword(userDetails.getPassword());

                    User updatedUser = userRepository.save(user);

                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}