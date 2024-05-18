package com.backend.backend.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.backend.backend.dto.CategoriaDTO;
import com.backend.backend.dto.CategoriasConSubCategoriasDTO;
import com.backend.backend.repositories.CategoriaRepository;
import com.backend.backend.services.CategoriaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> list() {
        return this.categoriaRepository.list();
    }

    @Override
    public CategoriaDTO insert(CategoriaDTO categoria) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("codigo", categoria.getCodigo());
        docData.put("descrip", categoria.getDescrip());
        docData.put("vigente", categoria.getVigente());

        return categoriaRepository.insert(categoria);
    }

    @Override
    public CategoriaDTO get(String idCategoria) {
        return categoriaRepository.get(idCategoria);
    }

    @Override
    public List<CategoriasConSubCategoriasDTO> getAllCategoriasConSubCategorias() {
        return null;
        // List<CategoriasConSubCategoriasDTO> res = new ArrayList<>();
        // List<SubCategoriaDTO> subCategorias;
        // CategoriasConSubCategoriasDTO tmp;
        // List<CategoriaDTO> categorias = this.list();
        // try {

        // for (CategoriaDTO c : categorias) {
        // subCategorias = subCategoriaService.getPorIdCategoria(c.getIdCategoria());
        // // TODO: El c se deberia porder castear a CategoriasConSubCategoriasDTO. no
        // me
        // // acuerdo como hacerlo
        // tmp = new CategoriasConSubCategoriasDTO();
        // tmp.setCodigo(c.getCodigo());
        // tmp.setDescrip(c.getDescrip());
        // tmp.setVigente(c.getVigente());
        // tmp.setIdCategoria(c.getIdCategoria());
        // tmp.setSubCategorias(subCategorias);
        // res.add(tmp);
        // }
        // return res;
        // } catch (Exception e) {
        // // TODO: handle exception
        // System.out.println(e);
        // return null;
        // }
    }
}
