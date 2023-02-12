package com.example.cookbook;

import com.example.cookbook.domain.Category;
import com.example.cookbook.domain.UnitOfMeasure;
import com.example.cookbook.repositories.CategoryRepository;
import com.example.cookbook.repositories.UnitOfMeasureRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@SpringBootApplication
public class CookbookApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(CookbookApplication.class, args);
        System.out.println("http://localhost:8080");
        for(String i: ctx.getEnvironment().getActiveProfiles())
            if(i.equals("default")) System.out.println("http://localhost:8080/h2-console");
    }
    @RequestMapping("/error")
    public String getError(){ return "error"; }

}
