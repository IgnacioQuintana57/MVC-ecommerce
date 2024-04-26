package com.backend.backend.models;

import org.json.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.json.JsonParser;

public class Db<T> {
    private String urlJSON;
    private Class<T> clazz;

    public Db(String urlJSON, Class<T> clazz) {
        this.urlJSON = urlJSON;
        this.clazz = clazz;
    }

    public T[] getData() {
        try {
            FileReader input = new FileReader(this.urlJSON);
            BufferedReader buffer = new BufferedReader(input);
            System.out.println(buffer);
            List<T> lista = new ArrayList<T>();
            String line;
            while ((line = buffer.readLine()) != null) {
                // Aquí debes implementar la lógica para convertir la línea leída en un objeto
                // de tipo T
                // Por ahora, simplemente la añadiré a la lista
                // Supongamos que tienes un método de parseo estático en la clase T
                // lista.add(clazz.getDeclaredConstructor(String.class).newInstance(line));
            }
            
            T[] ret = (T[]) lista.toArray();
            return ret;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
            throw new Error(e.getCause());

        }
    }
}
