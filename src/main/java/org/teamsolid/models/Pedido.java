package org.teamsolid.models;

public class Pedido {
    private int id;
    private Cliente cliente;
    private ItemPedido item;
    private boolean pagado;

    public Pedido(int id, Cliente cliente, ItemPedido item, boolean pagado) {
        this.id = id;
        this.cliente = cliente;
        this.item = item;
        this.pagado = pagado;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ItemPedido getItem() {
        return item;
    }

    public boolean isPagado() {
        return pagado;
    }

    public double getTotal() {
        return item.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", item=" + item +
                ", pagado=" + pagado +
                '}';
    }
}
