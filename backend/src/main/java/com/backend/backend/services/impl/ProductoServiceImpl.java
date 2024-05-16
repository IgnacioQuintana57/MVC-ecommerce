package com.backend.backend.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.CategoriaDTO;
import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.dto.SubCategoriaDTO;
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

        ApiFuture<QuerySnapshot> query = firebase.getFirestore().collection("productos").get();
        try {
            for (DocumentSnapshot doc : query.get().getDocuments()) {
                producto = doc.toObject(ProductoDTO.class);
                producto.setIdProducto(doc.getId());
                res.add(producto);
            }
            return res;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public ProductoDTO get(String idProducto) {
        DocumentReference productoRef = firebase.getFirestore().collection("productos").document(idProducto);
        try {
            DocumentSnapshot snap = productoRef.get().get();
            if (snap.exists()) {
                ProductoDTO prod = snap.toObject(ProductoDTO.class);
                prod.setIdProducto(snap.getId());
                return prod;
            }
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    @Override
    public ProductoDTO insert(ProductoDTO pr) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("descrip", pr.getDescrip());
        docData.put("precio", pr.getPrecio());
        docData.put("vigente", pr.isVigente());
        docData.put("conStock", pr.isConStock());
        docData.put("cantStock", pr.getCantStock());
        docData.put("idCategoria", pr.getIdSubCategoria());
        docData.put("linkImagen", pr.getLinkImagen());

        CollectionReference productos = firebase.getFirestore().collection("productos");
        DocumentReference docRef = productos.document();
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
    public List<ProductoDTO> insertMultiple(List<ProductoDTO> productos) {
        List<ProductoDTO> res = new ArrayList<>();

        for (ProductoDTO producto : productos) {
            ProductoDTO tmp = insert(producto);
            if (tmp != null) {
                res.add(tmp);
            } else {
                // Manejar el caso donde la inserción falló, si es necesario
                System.out.println("Failed to insert sub-category: " + producto);
            }
        }

        return res;
    }

}
