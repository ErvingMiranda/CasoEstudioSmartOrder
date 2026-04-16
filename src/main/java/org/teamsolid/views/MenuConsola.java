package org.teamsolid.views;

import org.teamsolid.models.Cliente;
import org.teamsolid.models.Pedido;
import org.teamsolid.models.Producto;
import org.teamsolid.models.ResultadoPago;
import org.teamsolid.payments.PagoEfectivo;
import org.teamsolid.payments.PagoTarjeta;
import org.teamsolid.services.ServicioClientes;
import org.teamsolid.services.ServicioPedidos;
import org.teamsolid.services.ServicioProductos;

import java.util.Scanner;

public class MenuConsola {
    private final ServicioClientes servicioClientes;
    private final ServicioProductos servicioProductos;
    private final ServicioPedidos servicioPedidos;
    private final Scanner scanner;

    public MenuConsola(ServicioClientes servicioClientes, ServicioProductos servicioProductos, ServicioPedidos servicioPedidos) {
        this.servicioClientes = servicioClientes;
        this.servicioProductos = servicioProductos;
        this.servicioPedidos = servicioPedidos;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> registrarProducto();
                case 3 -> crearPedido();
                case 4 -> verClientes();
                case 5 -> verProductos();
                case 6 -> verPedidos();
                case 7 -> verReportes();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("===== SMARTORDER =====");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar producto");
        System.out.println("3. Crear pedido");
        System.out.println("4. Ver clientes");
        System.out.println("5. Ver productos");
        System.out.println("6. Ver pedidos");
        System.out.println("7. Ver reportes");
        System.out.println("0. Salir");
    }

    private void registrarCliente() {
        int id = leerEntero("ID del cliente: ");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        Cliente cliente = new Cliente(id, nombre, telefono, direccion);
        servicioClientes.registrarCliente(cliente);

        System.out.println("Cliente registrado correctamente.");
    }

    private void registrarProducto() {
        int id = leerEntero("ID del producto: ");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        double precio = leerDouble("Precio: ");
        System.out.print("¿Está disponible? (true/false): ");
        boolean disponible = Boolean.parseBoolean(scanner.nextLine());

        Producto producto = new Producto(id, nombre, precio, disponible);
        servicioProductos.registrarProducto(producto);

        System.out.println("Producto registrado correctamente.");
    }

    private void crearPedido() {
        int idPedido = leerEntero("ID del pedido: ");
        int idCliente = leerEntero("ID del cliente: ");

        Cliente cliente = servicioClientes.buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Pedido pedido = new Pedido(idPedido, cliente, false);

        String continuar;
        do {
            int idProducto = leerEntero("ID del producto: ");
            Producto producto = servicioProductos.buscarProducto(idProducto);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
            } else {
                int cantidad = leerEntero("Cantidad: ");
                servicioPedidos.agregarProductoAPedido(pedido, producto, cantidad);
                System.out.println("Producto agregado al pedido.");
            }

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("Método de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        int opcionPago = leerEntero("Seleccione una opción: ");

        ResultadoPago resultado;
        if (opcionPago == 1) {
            resultado = servicioPedidos.procesarPago(pedido, new PagoEfectivo());
        } else if (opcionPago == 2) {
            resultado = servicioPedidos.procesarPago(pedido, new PagoTarjeta());
        } else {
            System.out.println("Método de pago inválido.");
            return;
        }

        System.out.println(resultado.getMensaje());
        System.out.println("Total del pedido: C$" + pedido.getTotal());
    }

    private void verClientes() {
        System.out.println("=== CLIENTES ===");
        for (Cliente cliente : servicioClientes.listarClientes()) {
            System.out.println(cliente);
        }
    }

    private void verProductos() {
        System.out.println("=== PRODUCTOS ===");
        for (Producto producto : servicioProductos.listarProductos()) {
            System.out.println(producto);
        }
    }

    private void verPedidos() {
        System.out.println("=== PEDIDOS ===");
        for (Pedido pedido : servicioPedidos.listarPedidos()) {
            System.out.println(pedido);
        }
    }

    private void verReportes() {
        System.out.println("=== REPORTES ===");
        System.out.println("Cantidad de pedidos realizados: " + servicioPedidos.cantidadPedidosRealizados());
        System.out.println("Total de ventas: C$" + servicioPedidos.calcularTotalVentas());
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}