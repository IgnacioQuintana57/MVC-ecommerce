package com.backend.backend.services;

import java.util.List;

import com.backend.backend.dto.FiltroProductosDTO;
import com.backend.backend.dto.ProductoDTO;

public interface ProductoService {

    List<ProductoDTO> list();

    ProductoDTO get(String idProducto);

    ProductoDTO insert(ProductoDTO pr);

    List<ProductoDTO> getProductosPorFiltro(FiltroProductosDTO filtro);

    List<ProductoDTO> insertMultiple(List<ProductoDTO> productos);

}
