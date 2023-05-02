package com.example.recipenowwebappbackend.controller;


import com.example.recipenowwebappbackend.dto.base.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/hello")
public class HelloController {


    @GetMapping()
    public ApiResponse findBookByBookId(@PathVariable Long bookId) {
        log.info("BookController: findBookByBookId() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Book fetched successfully.", "Hello");
    }


}
