package com.backend.backend.models;

public class Producto {
    private int idProducto;
    private String descrip;
    private String precio;

    public Producto(int idProducto, String descrip, String precio) {
        this.idProducto = idProducto;
        this.descrip = descrip;
        this.precio = precio;
    }
}
