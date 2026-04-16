package org.teamsolid.repository;

import org.teamsolid.interfaces.RepositorioProductos;
import org.teamsolid.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProductosMemoria implements RepositorioProductos {
    private List<Producto> productos = new ArrayList<>();

    @Override
    public void guardar(Producto producto) {
        productos.add(producto);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }

    @Override
    public Producto buscarPorId(String id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String id) {
        productos.removeIf(producto -> producto.getId().equals(id));
    }
}