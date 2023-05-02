package com.example.recipenowwebappbackend.controller;

import com.example.recipenowwebappbackend.controller.base.BaseController;
import com.example.recipenowwebappbackend.dto.UserDto;
import com.example.recipenowwebappbackend.dto.base.response.ApiResponse;
import com.example.recipenowwebappbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController implements BaseController<UserService> {
    private final UserService userService;

    @Override
    public UserService getService() {
        return userService;
    }


    @GetMapping("/find-is-email-exists/{email}")
    public ApiResponse checkIsEmailAlreadyExists(@PathVariable String email) {
        log.info("UserController: checkIsEmailAlreadyExists() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Email existence checked successfully.", getService().isUserExistsByEmail(email));
    }

    @PostMapping
    public ApiResponse createUser(@RequestBody UserDto userDto) {
        log.info("UserController: createUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User created successfully.", getService().create(userDto));
    }


    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse updateUser(@RequestBody UserDto userDto) {
        log.info("UserController: updateUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User updated successfully.", getService().update(userDto, userDto.getId()));
    }
}
