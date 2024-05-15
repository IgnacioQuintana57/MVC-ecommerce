package com.backend.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.services.ProductoService;
import com.google.rpc.context.AttributeContext.Response;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<ProductoDTO>> getProducto() {
        return new ResponseEntity<List<ProductoDTO>>(service.list(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Boolean> insert(@RequestBody ProductoDTO pr) {
        return new ResponseEntity<Boolean>(service.insert(pr), HttpStatus.OK);
    }

}
