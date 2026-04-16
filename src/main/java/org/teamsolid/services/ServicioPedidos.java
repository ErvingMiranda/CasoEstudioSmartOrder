package org.teamsolid.services;

import org.teamsolid.interfaces.MetodoPago;
import org.teamsolid.interfaces.RepositorioPedidos;
import org.teamsolid.models.ItemPedido;
import org.teamsolid.models.Pedido;
import org.teamsolid.models.Producto;
import org.teamsolid.models.ResultadoPago;

import java.util.List;

public class ServicioPedidos {
    private RepositorioPedidos repositorioPedidos;

    public ServicioPedidos(RepositorioPedidos repositorioPedidos) {
        this.repositorioPedidos = repositorioPedidos;
    }

    public void registrarPedido(Pedido pedido) {
        repositorioPedidos.guardar(pedido);
    }

    public void agregarProductoAPedido(Pedido pedido, Producto producto, int cantidad) {
        if (!producto.isDisponible()) {
            throw new IllegalArgumentException("El producto no está disponible.");
        }

        pedido.agregarItem(new ItemPedido(producto, cantidad));
    }

    public ResultadoPago procesarPago(Pedido pedido, MetodoPago metodoPago) {
        if (pedido.getItems().isEmpty()) {
            return new ResultadoPago(false, "No se puede pagar un pedido vacío.");
        }

        if (pedido.isPagado()) {
            return new ResultadoPago(false, "El pedido ya está pagado.");
        }

        ResultadoPago resultado = metodoPago.procesarPago(pedido.getTotal());

        if (resultado.isExitoso()) {
            pedido.marcarComoPagado();
            repositorioPedidos.guardar(pedido);
        }

        return resultado;
    }

    public List<Pedido> listarPedidos() {
        return repositorioPedidos.obtenerTodos();
    }

    public double calcularTotalVentas() {
        double total = 0;
        for (Pedido pedido : repositorioPedidos.obtenerTodos()) {
            total += pedido.getTotal();
        }
        return total;
    }

    public int cantidadPedidosRealizados() {
        return repositorioPedidos.obtenerTodos().size();
    }
}