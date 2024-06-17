package com.backend.backend.services;

import java.util.List;

import com.backend.backend.dto.FiltroProductosDTO;
import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.error.BadReqException;
import com.backend.backend.error.NotFoundException;

public interface ProductoService {

    List<ProductoDTO> list();

    ProductoDTO get(String idProducto) throws NotFoundException;

    ProductoDTO insert(ProductoDTO pr) throws BadReqException;

    List<ProductoDTO> getProductosPorFiltro(FiltroProductosDTO filtro) throws NotFoundException;

    List<ProductoDTO> insertMultiple(List<ProductoDTO> productos) throws BadReqException;

}
