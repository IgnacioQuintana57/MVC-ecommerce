package com.backend.backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;




@RestController()
public class Test {
    
    @GetMapping("/test")
    public String getMethodName() {
        System.out.println("HOlas");
        return new String("Hola Desde");
    }

    
}
