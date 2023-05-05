package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.dtos.UserDto;
import com.example.recipenowwebappbackend.exceptions.UserNotFoundException;
import com.example.recipenowwebappbackend.models.SetupPaginator;
import com.example.recipenowwebappbackend.models.UserFilter;
import com.example.recipenowwebappbackend.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
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

    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser() {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.FOUND);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.FOUND);
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

