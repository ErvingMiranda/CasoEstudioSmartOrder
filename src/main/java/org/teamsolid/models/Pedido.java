package org.teamsolid.models;

import org.teamsolid.utils.GeneradorId;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String id;
    private Cliente cliente;
    private boolean pagado;
    private List<ItemPedido> items;

    public Pedido(Cliente cliente, boolean pagado) {
        this.id = GeneradorId.generar();
        this.cliente = cliente;
        this.pagado = pagado;
        this.items = new ArrayList<>();
    }

    public String getId() {
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

    public void eliminarItem(String idProducto) {
        items.removeIf(item -> item.getProducto().getId().equals(idProducto));
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
                "id='" + id + '\'' +
                ", cliente=" + cliente.getNombre() +
                ", pagado=" + pagado +
                ", items=" + items +
                ", total=" + getTotal() +
                '}';
    }
}