package org.teamsolid.repository;

import org.teamsolid.interfaces.RepositorioClientes;
import org.teamsolid.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class RepositorioClientesMemoria implements RepositorioClientes {
    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public void guardar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return new ArrayList<>(clientes);
    }

    @Override
    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
}