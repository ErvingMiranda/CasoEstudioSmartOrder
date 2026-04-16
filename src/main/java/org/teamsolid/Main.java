package org.teamsolid;

import org.teamsolid.interfaces.RepositorioClientes;
import org.teamsolid.interfaces.RepositorioPedidos;
import org.teamsolid.interfaces.RepositorioProductos;
import org.teamsolid.models.Cliente;
import org.teamsolid.models.Pedido;
import org.teamsolid.models.Producto;
import org.teamsolid.models.ResultadoPago;
import org.teamsolid.payments.PagoEfectivo;
import org.teamsolid.payments.PagoTarjeta;
import org.teamsolid.repository.RepositorioClientesMemoria;
import org.teamsolid.repository.RepositorioPedidosMemoria;
import org.teamsolid.repository.RepositorioProductosMemoria;
import org.teamsolid.services.ServicioClientes;
import org.teamsolid.services.ServicioPedidos;
import org.teamsolid.services.ServicioProductos;

public class Main {
    public static void main(String[] args) {
        RepositorioClientes repoClientes = new RepositorioClientesMemoria();
        RepositorioProductos repoProductos = new RepositorioProductosMemoria();
        RepositorioPedidos repoPedidos = new RepositorioPedidosMemoria();

        ServicioClientes servicioClientes = new ServicioClientes(repoClientes);
        ServicioProductos servicioProductos = new ServicioProductos(repoProductos);
        ServicioPedidos servicioPedidos = new ServicioPedidos(repoPedidos);

        Cliente cliente1 = new Cliente(1, "Erving Miranda", "88329157", "Managua");
        Cliente cliente2 = new Cliente(2, "Carlos Umaña", "77778888", "Masaya");

        servicioClientes.registrarCliente(cliente1);
        servicioClientes.registrarCliente(cliente2);

        Producto producto1 = new Producto(1, "Gaseosa", 1.99, true);
        Producto producto2 = new Producto(2, "Slice de pizza", 5.99, true);
        Producto producto3 = new Producto(3, "Hamburguesa", 8.50, true);

        servicioProductos.registrarProducto(producto1);
        servicioProductos.registrarProducto(producto2);
        servicioProductos.registrarProducto(producto3);

        Pedido pedido1 = new Pedido(1, cliente1, false);
        servicioPedidos.agregarProductoAPedido(pedido1, producto1, 2);
        servicioPedidos.agregarProductoAPedido(pedido1, producto2, 1);

        ResultadoPago resultado1 = servicioPedidos.procesarPago(pedido1, new PagoTarjeta());
        System.out.println(resultado1.getMensaje());

        Pedido pedido2 = new Pedido(2, cliente2, false);
        servicioPedidos.agregarProductoAPedido(pedido2, producto3, 1);
        servicioPedidos.agregarProductoAPedido(pedido2, producto1, 3);

        ResultadoPago resultado2 = servicioPedidos.procesarPago(pedido2, new PagoEfectivo());
        System.out.println(resultado2.getMensaje());

        System.out.println("\n=== CLIENTES REGISTRADOS ===");
        for (Cliente cliente : servicioClientes.listarClientes()) {
            System.out.println(cliente);
        }

        System.out.println("\n=== PRODUCTOS REGISTRADOS ===");
        for (Producto producto : servicioProductos.listarProductos()) {
            System.out.println(producto);
        }

        System.out.println("\n=== PEDIDOS REALIZADOS ===");
        for (Pedido pedido : servicioPedidos.listarPedidos()) {
            System.out.println(pedido);
        }

        System.out.println("\n=== REPORTES ===");
        System.out.println("Cantidad de pedidos realizados: " + servicioPedidos.cantidadPedidosRealizados());
        System.out.println("Total de ventas: C$" + servicioPedidos.calcularTotalVentas());
    }
}