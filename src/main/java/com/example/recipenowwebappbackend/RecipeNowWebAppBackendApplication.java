package com.example.recipenowwebappbackend;

import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class RecipeNowWebAppBackendApplication {
    @Autowired
    static
    InteractionRepository interactionRepository;
    public static void main(String[] args) {

        SpringApplication.run(RecipeNowWebAppBackendApplication.class, args);
    }

}
