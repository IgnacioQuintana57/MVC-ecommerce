package com.backend.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.FiltroProductosDTO;
import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.services.ApiKeyValidationService;
import com.backend.backend.services.ProductoService;

@RestController()
@RequestMapping("api/v1/producto")
public class ProductoController {
    @Autowired
    private ProductoService service;
    @Autowired
    private ApiKeyValidationService apiKey;

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoDTO> get(@PathVariable(value = "idProducto") String idProducto) {
        return new ResponseEntity<ProductoDTO>(service.get(idProducto), HttpStatus.OK);
    }

    @GetMapping("/getProductosPorFiltro")
    public ResponseEntity<List<ProductoDTO>> getProductosPorFiltro(
            @RequestParam(name = "idCategoria", required = false) String idCategoria,
            @RequestParam(name = "idSubCategoria", required = false) String idSubCategoria,
            @RequestParam(name = "descrip", required = false) String descrip) {
        FiltroProductosDTO filtro = new FiltroProductosDTO();
        filtro.setDescrip("");
        filtro.setIdCategoria(idCategoria.length() == 20 ? idCategoria : null);
        filtro.setIdSubCategoria(idSubCategoria.length() == 20 ? idSubCategoria : null);
        return new ResponseEntity<List<ProductoDTO>>(service.getProductosPorFiltro(filtro), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductoDTO>> list() {
        return new ResponseEntity<List<ProductoDTO>>(service.list(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ProductoDTO> insert(@RequestBody ProductoDTO pr,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (!apiKey.isValidApiKeyInsert(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<ProductoDTO>(service.insert(pr), HttpStatus.OK);
    }

    @PostMapping("/newMultiple")
    public ResponseEntity<List<ProductoDTO>> insertMultiple(@Validated @RequestBody List<ProductoDTO> pr,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (!apiKey.isValidApiKeyInsert(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<List<ProductoDTO>>(service.insertMultiple(pr), HttpStatus.OK);
    }

}
