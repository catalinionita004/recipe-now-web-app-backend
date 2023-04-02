package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.models.SetupPaginator;
import com.example.recipenowwebappbackend.models.UserFilter;
import com.example.recipenowwebappbackend.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping(value = "/activation")
    public ResponseEntity<?> enableNewUser(@RequestBody UserFilter userFilter) {
        userService.enableUser(userFilter.username());
        return ResponseEntity.ok("Account enabled successfully");
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping("/role")
    public ResponseEntity<?> updateUserRole(@RequestBody UserFilter userFilter) {
        userService.updateUserRole(userFilter.username(), userFilter.role());
        return ResponseEntity.ok("User role updated successfully");
    }

//    @GetMapping("/pages")
//    public ResponseEntity<List<?>> getOrderByPage(@RequestParam Integer page) {
//        return new ResponseEntity<>(userService.getOrderByPage(page), HttpStatus.OK);
//    }
//
//    @GetMapping("/setup-paginator")
//    public ResponseEntity<SetupPaginator> setupPaginator() {
//        return new ResponseEntity<>(userService.getSetupPaginator(), HttpStatus.OK);
//    }
}

