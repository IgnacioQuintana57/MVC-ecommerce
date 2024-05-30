package com.backend.backend.repositories.impl;

import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.firebase.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductoRepositoryImpl {

    private final CollectionReference productosReference;   //Para que se cree solo una instancia

    public ProductoRepositoryImpl(FirebaseInitializer firebaseInitializer) {
        this.productosReference = firebaseInitializer.getFirestore().collection("productos");
    }

    public List<ProductoDTO> list() {
        List<ProductoDTO> res = new ArrayList<>();
        ProductoDTO producto;

        ApiFuture<QuerySnapshot> query = productosReference.get();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                producto = doc.toObject(ProductoDTO.class);
                assert producto != null;
                producto.setIdProducto(doc.getId());
                res.add(producto);
            }
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public ProductoDTO get(String idProducto) {
        DocumentReference productoRef = productosReference.document(idProducto);
        try{
            DocumentSnapshot snap = productoRef.get().get();
            if (snap.exists()) {
                ProductoDTO prod = snap.toObject(ProductoDTO.class);
                assert prod != null;
                prod.setIdProducto(snap.getId());
                return prod;
            }
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public ProductoDTO insert(Map<String,Object> pr) {
        DocumentReference docRef = productosReference.document();
        ApiFuture<WriteResult> wrApi = docRef.set(pr);
        try {
            if (null != wrApi.get()) {
                return get(docRef.getId());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
