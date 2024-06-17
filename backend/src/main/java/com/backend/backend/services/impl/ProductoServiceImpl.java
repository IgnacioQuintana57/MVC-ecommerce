package com.backend.backend.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.backend.backend.dto.FiltroProductosDTO;
import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.error.BadReqException;
import com.backend.backend.error.NotFoundException;
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
    public ProductoDTO get(String idProducto) throws NotFoundException {
        ProductoDTO ret = productoRepositoryImpl.get(idProducto);
        if (ret != null)
            return ret;
        else
            throw new NotFoundException("No se encontro el producto solicitado");

    }

    @Override
    public ProductoDTO insert(ProductoDTO pr) throws BadReqException {

        if ((pr.getDescrip() == null)) {
            throw new BadReqException("No hay descripción");
        }
        if ((pr.getPrecio() == null)) {
            throw new BadReqException("No hay precio");
        }
        if ((pr.getCantStock() == 0)) {
            throw new BadReqException("No hay stock");
        }
        if ((pr.getIdSubCategoria() == null) || pr.getIdSubCategoria().length() != 20) {
            throw new BadReqException("No hay subcategoría");
        }
        if ((pr.getIdCategoria() == null) || pr.getIdCategoria().length() != 20) {
            throw new BadReqException("No hay categoría");
        }
        if ((pr.getLinkImagen() == null)) {
            throw new BadReqException("No hay imagen");
        }
        if ((pr.getDestacado() == null)) {
            throw new BadReqException("No se informó el estado de destacado");
        }

        Map<String, Object> docData = new HashMap<>();
        docData.put("descrip", pr.getDescrip());
        docData.put("precio", pr.getPrecio());
        docData.put("vigente", pr.isVigente());
        docData.put("conStock", pr.isConStock());
        docData.put("cantStock", pr.getCantStock());
        docData.put("idSubCategoria", pr.getIdSubCategoria());
        docData.put("idCategoria", pr.getIdCategoria());
        docData.put("linkImagen", pr.getLinkImagen());
        docData.put("destacado", pr.getDestacado());
        return productoRepositoryImpl.insert(docData);
    }

    @Override
    public List<ProductoDTO> insertMultiple(List<ProductoDTO> productos) throws BadReqException {
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
    public List<ProductoDTO> getProductosPorFiltro(FiltroProductosDTO filtro) throws NotFoundException {
        // TODO Auto-generated method stub
        List<ProductoDTO> ret = productoRepositoryImpl.getProductosPorFiltro(filtro);
        if (ret.isEmpty() || ret == null) {
            throw new NotFoundException("No se encontraron productos con los filtros seleccionados.");
        }
        return ret;
    }
}
