package com.backend.backend.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.CategoriaDTO;
import com.backend.backend.firebase.FirebaseInitializer;
import com.backend.backend.services.CategoriaService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public List<CategoriaDTO> list() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CategoriaDTO insert(CategoriaDTO categoria) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("codigo", categoria.getCodigo());
        docData.put("descrip", categoria.getDescrip());
        docData.put("vigente", categoria.getVigente());
        CollectionReference categorias = firebase.getFirestore().collection("categorias");
        DocumentReference docRef = categorias.document();

        ApiFuture<WriteResult> wrApi = docRef.set(docData);
        try {
            if (null != wrApi.get()) {
                return get(docRef.getId());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CategoriaDTO get(String idCategoria) {
        DocumentReference categoriaRef = firebase.getFirestore().collection("categorias").document(idCategoria);
        try {
            DocumentSnapshot catSnap = categoriaRef.get().get();
            if (catSnap.exists()) {
                CategoriaDTO cat = catSnap.toObject(CategoriaDTO.class);
                cat.setIdCategoria(catSnap.getId());
                System.out.println("Categoria: " + cat);
                return cat;
            }
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
