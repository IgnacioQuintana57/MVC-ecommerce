package com.backend.backend.services;

import java.util.List;

import com.backend.backend.dto.CategoriasConSubCategoriasDTO;
import com.backend.backend.dto.SubCategoriaDTO;

public interface SubCategoriaService {

    SubCategoriaDTO get(String idSubCategoria);

    List<SubCategoriaDTO> getPorIdCategoria(String idCategoria);

    SubCategoriaDTO insert(SubCategoriaDTO subCategoria);

    List<SubCategoriaDTO> insertMultiple(List<SubCategoriaDTO> subCategorias);

    List<SubCategoriaDTO> list();

}
