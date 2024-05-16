package com.backend.backend.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.CategoriaDTO;
import com.backend.backend.dto.CategoriasConSubCategoriasDTO;
import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.dto.SubCategoriaDTO;
import com.backend.backend.firebase.FirebaseInitializer;
import com.backend.backend.services.CategoriaService;
import com.backend.backend.services.SubCategoriaService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private FirebaseInitializer firebase;
    @Autowired
    private SubCategoriaService subCategoriaService;

    @Override
    public List<CategoriaDTO> list() {
        List<CategoriaDTO> res = new ArrayList<>();
        CategoriaDTO categoria;

        ApiFuture<QuerySnapshot> query = firebase.getFirestore().collection("categorias").get();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                categoria = doc.toObject(CategoriaDTO.class);
                categoria.setIdCategoria(doc.getId());
                res.add(categoria);
            }
            return res;
        } catch (Exception e) {
            return null;
        }
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

    @Override
    public List<CategoriasConSubCategoriasDTO> getAllCategoriasConSubCategorias() {
        List<CategoriasConSubCategoriasDTO> res = new ArrayList<>();
        List<SubCategoriaDTO> subCategorias;
        CategoriasConSubCategoriasDTO tmp;
        List<CategoriaDTO> categorias = this.list();
        try {

            for (CategoriaDTO c : categorias) {
                subCategorias = subCategoriaService.getPorIdCategoria(c.getIdCategoria());
                // TODO: El c se deberia porder castear a CategoriasConSubCategoriasDTO. no me
                // acuerdo como hacerlo
                tmp = new CategoriasConSubCategoriasDTO();
                tmp.setCodigo(c.getCodigo());
                tmp.setDescrip(c.getDescrip());
                tmp.setVigente(c.getVigente());
                tmp.setIdCategoria(c.getIdCategoria());
                tmp.setSubCategorias(subCategorias);
                res.add(tmp);
            }
            return res;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return null;
        }
    }
}
