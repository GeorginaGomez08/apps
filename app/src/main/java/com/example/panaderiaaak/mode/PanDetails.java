package com.example.panaderiaaak.mode;

public class PanDetails {
    private String nombre,descripcion,precio;
    int panId;
    public int getPanId() {
        return panId;
    }

    public void setPanId(int panId) {
        this.panId = panId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
