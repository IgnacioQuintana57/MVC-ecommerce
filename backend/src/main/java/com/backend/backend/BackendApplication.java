package com.backend.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.backend.backend.models.Db;
import com.backend.backend.models.Producto;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		
		Db<Producto> productos = new Db<Producto>("backend/src/main/java/com/backend/backend/db/productos.json", Producto.class);
		productos.getData();
		SpringApplication.run(BackendApplication.class, args);
	}

}
