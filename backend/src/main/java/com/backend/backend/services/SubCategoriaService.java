package com.backend.backend.services;

import java.util.List;

import com.backend.backend.dto.SubCategoriaDTO;
import com.backend.backend.error.NotFoundException;

public interface SubCategoriaService {

    SubCategoriaDTO get(String idSubCategoria) throws NotFoundException;

    List<SubCategoriaDTO> getPorIdCategoria(String idCategoria) throws NotFoundException;

    SubCategoriaDTO insert(SubCategoriaDTO subCategoria);

    List<SubCategoriaDTO> insertMultiple(List<SubCategoriaDTO> subCategorias);

    List<SubCategoriaDTO> list() throws NotFoundException;

}
