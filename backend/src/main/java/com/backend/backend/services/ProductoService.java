package com.backend.backend.services;

import java.util.List;

import com.backend.backend.dto.ProductoDTO;

public interface ProductoService {

    List<ProductoDTO> list();

    ProductoDTO getProducto(String id);

    Boolean insert(ProductoDTO pr);

}
