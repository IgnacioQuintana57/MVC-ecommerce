package com.backend.backend.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.backend.backend.dto.FiltroProductosDTO;
import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.repositories.impl.ProductoRepositoryImpl;
import com.backend.backend.services.ProductoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepositoryImpl productoRepositoryImpl; // Unica instancia

    @Override
    public List<ProductoDTO> list() {
        return productoRepositoryImpl.list();
    }

    @Override
    public ProductoDTO get(String idProducto) {
        return productoRepositoryImpl.get(idProducto);
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
        return productoRepositoryImpl.insert(docData);
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

    @Override
    public List<ProductoDTO> getProductosPorFiltro(FiltroProductosDTO filtro) {
        // TODO Auto-generated method stub
        return productoRepositoryImpl.getProductosPorFiltro(filtro);
    }
}