package org.teamsolid.models;

import org.teamsolid.utils.GeneradorId;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private boolean disponible;

    public Producto(String nombre, double precio, boolean disponible) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        this.id = GeneradorId.generar();
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", disponible=" + disponible +
                '}';
    }
}