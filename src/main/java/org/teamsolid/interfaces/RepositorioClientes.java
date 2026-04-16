package org.teamsolid.interfaces;

import org.teamsolid.models.Cliente;
import java.util.List;

public interface RepositorioClientes {
    void guardar(Cliente cliente);
    List<Cliente> obtenerTodos();
    Cliente buscarPorId(String id);
    void eliminar(String id);
}