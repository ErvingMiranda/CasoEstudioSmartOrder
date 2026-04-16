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

    public Cliente buscarCliente(String id) {
        return repositorioClientes.buscarPorId(id);
    }

    public void editarCliente(String id, String nombre, String telefono, String direccion) {
        Cliente cliente = repositorioClientes.buscarPorId(id);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);
            cliente.setDireccion(direccion);
        }
    }

    public void eliminarCliente(String id) {
        repositorioClientes.eliminar(id);
    }
}