package com.example.cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class CookbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookbookApplication.class, args);
        System.out.println("http://localhost:8080");
        System.out.println("BALALAertasd78uuuui8iiiL");
    }
    @RequestMapping("/error")
    public String getError(){ return "error"; }

}
