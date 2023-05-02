package com.example.recipenowwebappbackend;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "src/main/java/com/example/recipenowwebappbackend")
public class RecipeNowWebAppBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(RecipeNowWebAppBackendApplication.class, args);
    }

}
