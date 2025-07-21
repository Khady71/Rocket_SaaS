package com.example.rocket_saas.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🔹 GET all users
    @GetMapping
    public ResponseEntity<List<UserAsso>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    // 🔹 GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserAsso> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // 🔹 POST create new user
    @PostMapping
    public ResponseEntity<UserAsso> addUser(@RequestBody UserAsso user) {
        userService.addNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // 🔹 PUT update user
    @PutMapping("/{id}")
    public ResponseEntity<UserAsso> updateUser(@PathVariable Long id, @RequestBody UserAsso user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // 🔹 DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 GET by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserAsso> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 GET users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserAsso>> getUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.findByRole(role));
    }

    // 🔹 GET users by association ID
    @GetMapping("/association/{associationId}")
    public ResponseEntity<List<UserAsso>> getUsersByAssociation(@PathVariable Long associationId) {
        return ResponseEntity.ok(userService.findByAssociationId(associationId));
    }
}
