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

    public List<ItemPedido> getItems() {
        return new ArrayList<>(items);
    }

    public void agregarItem(ItemPedido item) {
        items.add(item);
    }

    public void eliminarItem(int idProducto) {
        items.removeIf(item -> item.getProducto().getId() == idProducto);
    }

    public double getTotal() {
        double total = 0;
        for (ItemPedido item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void marcarComoPagado() {
        this.pagado = true;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNombre() +
                ", pagado=" + pagado +
                ", items=" + items +
                ", total=" + getTotal() +
                '}';
    }
}