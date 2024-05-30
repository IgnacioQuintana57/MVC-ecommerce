package com.backend.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.CategoriaDTO;
import com.backend.backend.services.CategoriaService;

@RestController()
@RequestMapping("/categoria")
public class CategoriasController {
    @Autowired
    private CategoriaService service;

    @GetMapping("/list")
    public ResponseEntity<List<CategoriaDTO>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> get(@PathVariable(value = "idCategoria") String idCategoria) {
        return new ResponseEntity<CategoriaDTO>(service.get(idCategoria), HttpStatus.OK);
    }

    @GetMapping("/getAllCategoriasConSubCategorias")
    public ResponseEntity getAllCategoriasConSubCategorias() {
        return new ResponseEntity(service.getAllCategoriasConSubCategorias(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO cate) {
        return new ResponseEntity<CategoriaDTO>(service.insert(cate), HttpStatus.OK);
    }
}