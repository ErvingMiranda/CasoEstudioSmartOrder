package org.teamsolid.services;

import org.teamsolid.interfaces.RepositorioClientes;
import org.teamsolid.models.Cliente;

import java.util.List;

public class ServicioClientes {
    private RepositorioClientes repositorioClientes;

    public ServicioClientes(RepositorioClientes repositorioClientes) {
        this.repositorioClientes = repositorioClientes;
    }

    public void registrarCliente(Cliente cliente) {
        repositorioClientes.guardar(cliente);
    }

    public List<Cliente> listarClientes() {
        return repositorioClientes.obtenerTodos();
    }

    public Cliente buscarCliente(int id) {
        return repositorioClientes.buscarPorId(id);
    }
}