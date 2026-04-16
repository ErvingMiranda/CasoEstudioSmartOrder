package org.teamsolid.interfaces;

import org.teamsolid.models.Producto;
import java.util.List;

public interface RepositorioProductos {
    void guardar(Producto producto);
    List<Producto> obtenerTodos();
    Producto buscarPorId(int id);
}