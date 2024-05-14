package com.backend.backend.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class ProductoDTO {
    private String id;
    private String descrip;
    private Float precio;
    private boolean vigente;
    private boolean conStock;
    private int cantStock;
    private String idCategoria;
}
