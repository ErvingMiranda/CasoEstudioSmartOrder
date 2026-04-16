package org.teamsolid;

import org.teamsolid.interfaces.RepositorioClientes;
import org.teamsolid.interfaces.RepositorioPedidos;
import org.teamsolid.interfaces.RepositorioProductos;
import org.teamsolid.repository.RepositorioClientesMemoria;
import org.teamsolid.repository.RepositorioPedidosMemoria;
import org.teamsolid.repository.RepositorioProductosMemoria;
import org.teamsolid.services.ServicioClientes;
import org.teamsolid.services.ServicioPedidos;
import org.teamsolid.services.ServicioProductos;
import org.teamsolid.views.MenuConsola;

public class Main {
    public static void main(String[] args) {
        RepositorioClientes repoClientes = new RepositorioClientesMemoria();
        RepositorioProductos repoProductos = new RepositorioProductosMemoria();
        RepositorioPedidos repoPedidos = new RepositorioPedidosMemoria();

        ServicioClientes servicioClientes = new ServicioClientes(repoClientes);
        ServicioProductos servicioProductos = new ServicioProductos(repoProductos);
        ServicioPedidos servicioPedidos = new ServicioPedidos(repoPedidos);

        MenuConsola menu = new MenuConsola(servicioClientes, servicioProductos, servicioPedidos);
        menu.iniciar();
    }
}