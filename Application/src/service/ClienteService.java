package service;

import model.Cliente;
import service.dao.ClienteDAO;

import java.util.List;
import java.util.Optional;

public class ClienteService {
    private static ClienteService instancia;
    private final ClienteDAO clienteDAO;

    private ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    public static ClienteService getInstancia() {
        if (instancia == null) {
            instancia = new ClienteService();
        }
        return instancia;
    }

    public List<Cliente> listar() {
        return clienteDAO.listar();
    }

    public Optional<Cliente> ver(int id) {
        return clienteDAO.ver(id);
    }

    public void crear(Cliente cliente) {
        clienteDAO.crear(cliente);
    }

    public void modificar(Cliente cliente) {
        clienteDAO.modificar(cliente);
    }

    public void eliminar(int id) {
        clienteDAO.eliminar(id);
    }

    public Optional<Cliente> buscarPorDireccion(String direccion) {
        return clienteDAO.buscarPorDireccion(direccion);
    }
}
