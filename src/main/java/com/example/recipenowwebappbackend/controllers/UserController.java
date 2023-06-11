package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.dtos.UserDto;
import com.example.recipenowwebappbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.FOUND);
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

