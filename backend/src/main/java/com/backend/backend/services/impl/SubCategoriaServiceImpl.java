package com.backend.backend.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.CategoriaDTO;
import com.backend.backend.dto.SubCategoriaDTO;
import com.backend.backend.firebase.FirebaseInitializer;
import com.backend.backend.repositories.SubCategoriaRepository;
import com.backend.backend.services.SubCategoriaService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

    private final SubCategoriaRepository subCategoriaRepository;

    @Override
    public SubCategoriaDTO get(String idSubCategoria) {
        return subCategoriaRepository.get(idSubCategoria);
    }

    @Override
    public SubCategoriaDTO insert(SubCategoriaDTO subCategoria) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("codigo", subCategoria.getCodigo());
        docData.put("descrip", subCategoria.getDescrip());
        docData.put("vigente", subCategoria.getVigente());
        docData.put("idCategoria", subCategoria.getIdCategoria());
        String idInsertado = subCategoriaRepository.insert(subCategoria);
        if (idInsertado != null)
            return subCategoriaRepository.get(idInsertado);
        else
            return null;
    }

    @Override
    public List<SubCategoriaDTO> list() {
        return subCategoriaRepository.list();
    }

    @Override
    public List<SubCategoriaDTO> insertMultiple(List<SubCategoriaDTO> subCategorias) {
        List<SubCategoriaDTO> insertedSubCategorias = new ArrayList<>();

        for (SubCategoriaDTO subCategoria : subCategorias) {
            SubCategoriaDTO insertedSubCategoria = insert(subCategoria);// ESTO DEBERIA LLAMAR AL insert del service o
                                                                        // del repository??
            if (insertedSubCategoria != null) {
                insertedSubCategorias.add(insertedSubCategoria);
            } else {
                System.out.println("Error al insertar la sub cateogira");
            }
        }

        return insertedSubCategorias;
    }

    @Override
    public List<SubCategoriaDTO> getPorIdCategoria(String idCategoria) {
        return subCategoriaRepository.getPorIdCategoria(idCategoria);
    }

}
