package org.teamsolid.interfaces;

import org.teamsolid.models.Pedido;
import java.util.List;

public interface RepositorioPedidos {
    void guardar(Pedido pedido);
    List<Pedido> obtenerTodos();
    Pedido buscarPorId(int id);
}