package com.backend.backend.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.firebase.FirebaseInitializer;
import com.backend.backend.services.ProductoService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Filter;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firestore.v1.Document;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired // Inyecta un servicio y permite administrar sus instancias
    private FirebaseInitializer firebase; // Genera una unica instancia y no multiples

    @Override
    public List<ProductoDTO> list() {
        List<ProductoDTO> res = new ArrayList<>();
        ProductoDTO producto;

        ApiFuture<QuerySnapshot> query = firebase.getFirestore().collection("categorias")
                .document("ebsyNqxFVxTqeXKOlTvl").collection("subCategorias").document("OPMp6Zdtr1ynpwO7TQSN")
                .collection("productos").get();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                producto = doc.toObject(ProductoDTO.class);
                producto.setId(doc.getId());
                res.add(producto);
            }
            return res;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public ProductoDTO getProducto(String id) {
        CollectionReference productos = firebase.getFirestore().collection("productos");
        // productos.list
        return null;
    }

    @Override
    public Boolean insert(ProductoDTO pr) {
        CollectionReference productos = firebase.getFirestore().collection("productos");
        Map<String, Object> docData = new HashMap<>();
        docData.put("descrip", pr.getDescrip());
        docData.put("precio", pr.getPrecio());
        docData.put("vigente", pr.isVigente());
        docData.put("conStock", pr.isConStock());
        docData.put("cantStock", pr.getCantStock());
        docData.put("idCategoria", pr.getIdCategoria());

        ApiFuture<WriteResult> writeResulteApiFuture = productos.document().create(docData);

        try {
            if (null != writeResulteApiFuture.get()) {
                System.out.println("Resultado insert:" + writeResulteApiFuture.get().toString());
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}
