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

import com.backend.backend.dto.SubCategoriaDTO;
import com.backend.backend.services.SubCategoriaService;

@RestController()
@RequestMapping("/subCategoria")
public class SubCategoriasController {
    @Autowired
    SubCategoriaService service;

    @GetMapping("/list")
    public ResponseEntity<List<SubCategoriaDTO>> list() {
        return new ResponseEntity<List<SubCategoriaDTO>>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{idSubCategoria}")
    public ResponseEntity<SubCategoriaDTO> get(@PathVariable(value = "idSubCategoria") String idSubCategoria) {
        return new ResponseEntity<SubCategoriaDTO>(service.get(idSubCategoria), HttpStatus.OK);
    }

    @GetMapping("/getPorIdCategoria/{idCategoria}")
    public ResponseEntity<List<SubCategoriaDTO>> getPorIdCategoria(
            @PathVariable(value = "idCategoria") String idCategoria) {
        return new ResponseEntity<List<SubCategoriaDTO>>(service.getPorIdCategoria(idCategoria), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<SubCategoriaDTO> insert(@RequestBody SubCategoriaDTO subCategoria) {
        return new ResponseEntity<SubCategoriaDTO>(service.insert(subCategoria), HttpStatus.OK);
    }

    @PostMapping("/newMultiple")
    public ResponseEntity<List<SubCategoriaDTO>> insertMultiple(@RequestBody List<SubCategoriaDTO> subCategorias) {
        return new ResponseEntity<List<SubCategoriaDTO>>(service.insertMultiple(subCategorias), HttpStatus.OK);
    }
}
