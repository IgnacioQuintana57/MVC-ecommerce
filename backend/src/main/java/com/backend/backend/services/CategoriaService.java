package com.backend.backend.services;

import java.util.List;

import com.backend.backend.dto.CategoriaDTO;
import com.backend.backend.dto.CategoriasConSubCategoriasDTO;

public interface CategoriaService {

    List<CategoriaDTO> list();

    List<CategoriasConSubCategoriasDTO> getAllCategoriasConSubCategorias();

    CategoriaDTO insert(CategoriaDTO categoria);

    CategoriaDTO get(String idCategoria);
}
