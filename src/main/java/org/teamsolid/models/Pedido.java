package org.teamsolid.models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private boolean pagado;
    private List<ItemPedido> items;

    public Pedido(int id, Cliente cliente, boolean pagado) {
        this.id = id;
        this.cliente = cliente;
        this.pagado = pagado;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void agregarItem(ItemPedido item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", pagado=" + pagado +
                ", items=" + items +
                '}';
    }
}
