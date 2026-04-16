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

    public MenuConsola(ServicioClientes servicioClientes,
                       ServicioProductos servicioProductos,
                       ServicioPedidos servicioPedidos) {
        this.servicioClientes = servicioClientes;
        this.servicioProductos = servicioProductos;
        this.servicioPedidos = servicioPedidos;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> administrarClientes();
                case 2 -> administrarProductos();
                case 3 -> administrarPedidos();
                case 4 -> verReportes();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("===== SMARTORDER =====");
        System.out.println("1. Administrar clientes");
        System.out.println("2. Administrar productos");
        System.out.println("3. Administrar pedidos");
        System.out.println("4. Ver reportes");
        System.out.println("0. Salir");
    }

    private void administrarClientes() {
        int opcion;
        do {
            System.out.println("\n=== ADMINISTRAR CLIENTES ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver clientes");
            System.out.println("3. Editar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> verClientes();
                case 3 -> editarCliente();
                case 4 -> eliminarCliente();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void administrarProductos() {
        int opcion;
        do {
            System.out.println("\n=== ADMINISTRAR PRODUCTOS ===");
            System.out.println("1. Registrar producto");
            System.out.println("2. Ver productos");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarProducto();
                case 2 -> verProductos();
                case 3 -> editarProducto();
                case 4 -> eliminarProducto();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void administrarPedidos() {
        int opcion;
        do {
            System.out.println("\n=== ADMINISTRAR PEDIDOS ===");
            System.out.println("1. Crear pedido");
            System.out.println("2. Ver pedidos");
            System.out.println("3. Eliminar pedido");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> crearPedido();
                case 2 -> verPedidos();
                case 3 -> eliminarPedido();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrarCliente() {
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");
        String direccion = leerTexto("Dirección: ");

        Cliente cliente = new Cliente(nombre, telefono, direccion);
        servicioClientes.registrarCliente(cliente);

        System.out.println("Cliente registrado correctamente.");
        System.out.println("ID generado: " + cliente.getId());
    }

    private void editarCliente() {
        verClientes();
        String id = leerTexto("ID del cliente a editar: ");

        Cliente cliente = servicioClientes.buscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        String nombre = leerTexto("Nuevo nombre: ");
        String telefono = leerTexto("Nuevo teléfono: ");
        String direccion = leerTexto("Nueva dirección: ");

        servicioClientes.editarCliente(id, nombre, telefono, direccion);
        System.out.println("Cliente editado correctamente.");
    }

    private void eliminarCliente() {
        verClientes();
        String id = leerTexto("ID del cliente a eliminar: ");

        Cliente cliente = servicioClientes.buscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        servicioClientes.eliminarCliente(id);
        System.out.println("Cliente eliminado correctamente.");
    }

    private void registrarProducto() {
        String nombre = leerTexto("Nombre: ");
        double precio = leerDouble("Precio: ");
        boolean disponible = Boolean.parseBoolean(leerTexto("¿Está disponible? (true/false): "));

        Producto producto = new Producto(nombre, precio, disponible);
        servicioProductos.registrarProducto(producto);

        System.out.println("Producto registrado correctamente.");
        System.out.println("ID generado: " + producto.getId());
    }

    private void editarProducto() {
        verProductos();
        String id = leerTexto("ID del producto a editar: ");

        Producto producto = servicioProductos.buscarProducto(id);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        String nombre = leerTexto("Nuevo nombre: ");
        double precio = leerDouble("Nuevo precio: ");
        boolean disponible = Boolean.parseBoolean(leerTexto("¿Disponible? (true/false): "));

        servicioProductos.editarProducto(id, nombre, precio, disponible);
        System.out.println("Producto editado correctamente.");
    }

    private void eliminarProducto() {
        verProductos();
        String id = leerTexto("ID del producto a eliminar: ");

        Producto producto = servicioProductos.buscarProducto(id);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        servicioProductos.eliminarProducto(id);
        System.out.println("Producto eliminado correctamente.");
    }

    private void crearPedido() {
        if (servicioClientes.listarClientes().isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        if (servicioProductos.listarProductos().isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("Seleccione el cliente:");
        verClientes();

        String idCliente = leerTexto("ID del cliente: ");
        Cliente cliente = servicioClientes.buscarCliente(idCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Pedido pedido = new Pedido(cliente, false);

        int opcion;
        do {
            System.out.println("\n=== EDICIÓN DEL PEDIDO ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto del pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Finalizar y pagar");
            System.out.println("0. Cancelar pedido");

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> {
                    verProductos();
                    String idProducto = leerTexto("ID del producto: ");
                    Producto producto = servicioProductos.buscarProducto(idProducto);

                    if (producto == null) {
                        System.out.println("Producto no encontrado.");
                    } else {
                        int cantidad = leerEntero("Cantidad: ");
                        servicioPedidos.agregarProductoAPedido(pedido, producto, cantidad);
                        System.out.println("Producto agregado.");
                    }
                }
                case 2 -> {
                    if (pedido.getItems().isEmpty()) {
                        System.out.println("El pedido no tiene productos.");
                    } else {
                        System.out.println("Productos actuales del pedido:");
                        for (var item : pedido.getItems()) {
                            System.out.println("ID: " + item.getProducto().getId() +
                                    " | " + item.getProducto().getNombre() +
                                    " | Cantidad: " + item.getCantidad());
                        }

                        String idProductoEliminar = leerTexto("ID del producto a eliminar: ");
                        servicioPedidos.eliminarProductoDePedido(pedido, idProductoEliminar);
                        System.out.println("Producto eliminado del pedido.");
                    }
                }
                case 3 -> System.out.println(pedido);
                case 4 -> finalizarPago(pedido);
                case 0 -> System.out.println("Pedido cancelado.");
                default -> System.out.println("Opción inválida.");
            }

            if (opcion == 4 && pedido.isPagado()) {
                break;
            }

        } while (opcion != 0);
    }

    private void finalizarPago(Pedido pedido) {
        if (pedido.getItems().isEmpty()) {
            System.out.println("No se puede pagar un pedido vacío.");
            return;
        }

        System.out.println("\nMétodo de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");

        int opcionPago = leerEntero("Seleccione: ");
        ResultadoPago resultado;

        if (opcionPago == 1) {
            resultado = servicioPedidos.procesarPago(pedido, new PagoEfectivo());
        } else if (opcionPago == 2) {
            resultado = servicioPedidos.procesarPago(pedido, new PagoTarjeta());
        } else {
            System.out.println("Método inválido.");
            return;
        }

        System.out.println(resultado.getMensaje());
        System.out.println("Total del pedido: C$" + pedido.getTotal());
        System.out.println("ID del pedido: " + pedido.getId());
    }

    private void eliminarPedido() {
        verPedidos();
        String id = leerTexto("ID del pedido a eliminar: ");

        Pedido pedido = servicioPedidos.buscarPedido(id);
        if (pedido == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }

        servicioPedidos.eliminarPedido(id);
        System.out.println("Pedido eliminado correctamente.");
    }

    private void verClientes() {
        System.out.println("=== CLIENTES ===");
        for (Cliente cliente : servicioClientes.listarClientes()) {
            System.out.println("ID: " + cliente.getId() + " | " + cliente.getNombre());
        }
    }

    private void verProductos() {
        System.out.println("=== PRODUCTOS ===");
        for (Producto producto : servicioProductos.listarProductos()) {
            System.out.println("ID: " + producto.getId()
                    + " | " + producto.getNombre()
                    + " | C$" + producto.getPrecio()
                    + " | Disponible: " + producto.isDisponible());
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
        System.out.println("Cantidad de pedidos: " + servicioPedidos.cantidadPedidosRealizados());
        System.out.println("Total ventas: C$" + servicioPedidos.calcularTotalVentas());
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}