package com.backend.backend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.firebase.FirebaseInitializer;
import com.backend.backend.services.ProductoService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired // Inyecta un servicio y permite administrar sus instancias
    private FirebaseInitializer firebase; // Genera una unica instancia y no multiples

    @Override
    public List<ProductoDTO> list() {
        List<ProductoDTO> res = new ArrayList<>();
        ProductoDTO producto;

        ApiFuture<QuerySnapshot> query = firebase.getFirestore().collection("productos").get();
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

}
