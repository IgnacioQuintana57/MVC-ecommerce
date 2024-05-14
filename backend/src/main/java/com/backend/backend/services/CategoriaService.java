package com.backend.backend.services;

import java.util.List;

import com.backend.backend.dto.CategoriaDTO;

public interface CategoriaService {

    List<CategoriaDTO> list();

    CategoriaDTO insert(CategoriaDTO categoria);

    CategoriaDTO get(String idCategoria);
}
