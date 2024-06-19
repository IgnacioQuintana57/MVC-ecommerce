package com.backend.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backend.backend.dto.ProductoDTO;
import com.backend.backend.error.BadReqException;
import com.backend.backend.error.NotFoundException;
import com.backend.backend.repositories.ProductoRepository;
import com.backend.backend.services.impl.ProductoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private ProductoDTO producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        producto = new ProductoDTO();
        producto.setIdProducto("12345678910123456789");
        producto.setDescrip("test");
        producto.setPrecio(23.124F);
        producto.setVigente(Boolean.TRUE);
        producto.setConStock(Boolean.TRUE);
        producto.setCantStock(10);
        producto.setIdSubCategoria("1234567891234567891");
        producto.setIdCategoria("1234567891234567891");
        producto.setLinkImagen("Link");
        producto.setDestacado(Boolean.FALSE);

    }

    @Test
    void testGetThrowBadRequest() throws Exception {
        assertThrows(BadReqException.class, () -> {
            productoService.get("shortId");
        });
        assertThrows(BadReqException.class, () -> {
            productoService.get("longId123123123123123");
        });
        assertThrows(BadReqException.class, () -> {
            productoService.get(null);
        });
    }

    @Test
    void testGetThrowNotFound() throws Exception {
        when(productoRepository.get(producto.getIdProducto())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> {
            productoService.get(producto.getIdProducto());
        });
    }

    @Test
    void testGet() throws Exception {
        when(productoRepository.get(producto.getIdProducto())).thenReturn(producto);
        ProductoDTO tmp = productoService.get(producto.getIdProducto());
        assertEquals(producto, tmp);
    }

    @Test
    void testGetProductosPorFiltro() {

    }

    @Test
    void testInsert() {

    }

    @Test
    void testInsertMultiple() {

    }

    @Test
    void testListThrowsNotFound() {
        when(productoRepository.list()).thenReturn(null);
        assertThrows(NotFoundException.class, () -> {
            productoService.list();
        });

        when(productoRepository.list()).thenReturn(new ArrayList<>());
        assertThrows(NotFoundException.class, () -> {
            productoService.list();
        });
    }

    @Test
    void testList() throws Exception {
        List<ProductoDTO> tmp = new ArrayList<>();
        tmp.add(producto);
        when(productoRepository.list()).thenReturn(tmp);
        List<ProductoDTO> ret = productoService.list();
        assertEquals(tmp, ret);
    }
}
