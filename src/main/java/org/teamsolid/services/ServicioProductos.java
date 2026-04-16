package org.teamsolid.services;

import org.teamsolid.interfaces.RepositorioProductos;
import org.teamsolid.models.Producto;

import java.util.List;

public class ServicioProductos {
    private RepositorioProductos repositorioProductos;

    public ServicioProductos(RepositorioProductos repositorioProductos) {
        this.repositorioProductos = repositorioProductos;
    }

    public void registrarProducto(Producto producto) {
        repositorioProductos.guardar(producto);
    }

    public List<Producto> listarProductos() {
        return repositorioProductos.obtenerTodos();
    }

    public Producto buscarProducto(String id) {
        return repositorioProductos.buscarPorId(id);
    }
}