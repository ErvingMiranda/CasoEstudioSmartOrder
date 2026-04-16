package org.teamsolid.repository;

import org.teamsolid.interfaces.RepositorioPedidos;
import org.teamsolid.models.Pedido;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPedidosMemoria implements RepositorioPedidos {
    private List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void guardar(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public List<Pedido> obtenerTodos() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public Pedido buscarPorId(String id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId().equals(id)) {
                return pedido;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String id) {
        pedidos.removeIf(pedido -> pedido.getId().equals(id));
    }
}