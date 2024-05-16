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
import com.backend.backend.services.SubCategoriaService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {
    @Autowired
    FirebaseInitializer firebase;

    @Override
    public SubCategoriaDTO get(String idSubCategoria) {
        DocumentReference subCategoriaRef = firebase.getFirestore().collection("subCategorias")
                .document(idSubCategoria);
        try {
            DocumentSnapshot subCatSnap = subCategoriaRef.get().get();
            if (subCatSnap.exists()) {
                SubCategoriaDTO subCategoria = subCatSnap.toObject(SubCategoriaDTO.class);
                subCategoria.setIdSubCategoria(subCatSnap.getId());
                return subCategoria;
            }
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    @Override
    public SubCategoriaDTO insert(SubCategoriaDTO subCategoria) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("codigo", subCategoria.getCodigo());
        docData.put("descrip", subCategoria.getDescrip());
        docData.put("vigente", subCategoria.getVigente());
        docData.put("idCategoria", subCategoria.getIdCategoria());
        CollectionReference subCategorias = firebase.getFirestore().collection("subCategorias");
        DocumentReference docRef = subCategorias.document();

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
    public List<SubCategoriaDTO> list() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public List<SubCategoriaDTO> insertMultiple(List<SubCategoriaDTO> subCategorias) {
        List<SubCategoriaDTO> insertedSubCategorias = new ArrayList<>();

        for (SubCategoriaDTO subCategoria : subCategorias) {
            SubCategoriaDTO insertedSubCategoria = insert(subCategoria);
            if (insertedSubCategoria != null) {
                insertedSubCategorias.add(insertedSubCategoria);
            } else {
                // Manejar el caso donde la inserción falló, si es necesario
                System.out.println("Failed to insert sub-category: " + subCategoria);
            }
        }

        return insertedSubCategorias;
    }

    @Override
    public List<SubCategoriaDTO> getPorIdCategoria(String idCategoria) {
        Query query = firebase.getFirestore().collection("subCategorias").whereEqualTo("vigente", true)
                .whereEqualTo("idCategoria", idCategoria);
        List<SubCategoriaDTO> res = new ArrayList<>();
        SubCategoriaDTO tmp;
        try {
            QuerySnapshot querySnap = query.get().get();
            for (DocumentSnapshot doc : querySnap) {
                tmp = doc.toObject(SubCategoriaDTO.class);
                tmp.setIdSubCategoria(doc.getId());
                res.add(tmp);
            }
            return res;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
