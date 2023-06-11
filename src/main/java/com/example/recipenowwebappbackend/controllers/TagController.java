package com.example.recipenowwebappbackend.controllers;

import com.example.recipenowwebappbackend.dtos.ApiResponse;
import com.example.recipenowwebappbackend.services.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/{id}")
    public ApiResponse getTagById(@PathVariable Long id) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "FIND tag with id" + id, tagService.findTagById(id));
    }

    @GetMapping("/name")
    public ApiResponse  getTagByName(@RequestParam String name) {
        System.out.println(name);
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "FIND tags starting with name" + name, tagService.findTagByName(name));
    }


}
