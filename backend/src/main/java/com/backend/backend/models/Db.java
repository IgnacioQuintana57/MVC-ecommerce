package com.backend.backend.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

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
            System.out.println("INPUT ACA" + input.getEncoding());
            BufferedReader buffer = new BufferedReader(input);
            StringBuilder sb = new StringBuilder();
            System.out.println(buffer);
            List<T> lista = new ArrayList<T>();
            String line;
            System.out.println("Soy el buffer" + buffer.toString());
            while ((line = buffer.readLine()) != null) {
                // Aquí debes implementar la lógica para convertir la línea leída en un objeto
                // de tipo T
                // Por ahora, simplemente la añadiré a la lista
                // Supongamos que tienes un método de parseo estático en la clase T
                // lista.add(clazz.getDeclaredConstructor(String.class).newInstance(line));
                if (line != "[" || line != "]")
                    sb.append(line);
                // System.out.println("Hola soy la linea: " + line);
            }
            System.out.println(sb);
[]            JSONObject json = new JSONObject(sb.toString());
            System.out.println(json.getInt("idProducto"));
            T[] ret = (T[]) lista.toArray();
            return ret;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
            throw new Error(e.getCause());

        }
    }
}
