package com.backend.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;

import org.springframework.web.bind.annotation.GetMapping;

@RestController()
public class Test {

    @GetMapping("/test")
    public String getMethodName() {
        char[] array = new char[100];
        try {
            // Creates a reader using the FileReader
            FileReader input = new FileReader("./src/main/java/com/backend/backend/db/productos.json");
            // Reads characters
            input.read(array);
            System.out.println("Data in  the file: ");
            System.out.println(array);
            // Closes the reader
            input.close();
            return "Hola Desde aca 2 ";
        }

        catch (Exception e) {
            e.getStackTrace();
            return "Error";
        }
    }


}
