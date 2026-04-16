package org.teamsolid;

import org.teamsolid.models.Cliente;
import org.teamsolid.models.ItemPedido;
import org.teamsolid.models.Pedido;
import org.teamsolid.models.Producto;

public class Main {
    public static void main(String[] args) {
        Cliente c = new Cliente(2106, "Erving", "+505 8832 9157", "MiCasa.com");
        Producto p1 = new Producto(1, "Gaseosa", 1.99, true);
        Producto p2 = new Producto(2, "Slice de pizza", 5.99, true);
        Pedido pedido = new Pedido(1, c, true);
        pedido.agregarItem(new ItemPedido(p1, 2));
        pedido.agregarItem(new ItemPedido(p2, 3));
        System.out.println("El total del pedido es: " + pedido.getTotal());
    }
}