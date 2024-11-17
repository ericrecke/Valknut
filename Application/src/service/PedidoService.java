package service;

import model.Pedido;
import model.PedidoProducto;
import service.dao.PedidoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PedidoService {
    private static PedidoService instancia;
    private final PedidoDAO pedidoDAO;

    private PedidoService() {
        pedidoDAO = new PedidoDAO();
    }

    public static PedidoService getInstancia() {
        if (instancia == null) {
            instancia = new PedidoService();
        }
        return instancia;
    }

    public List<Pedido> listar() {
        return pedidoDAO.listar();
    }

    public Optional<Pedido> ver(int id) {
        return pedidoDAO.ver(id);
    }

    public void crear(Pedido pedido) {
        pedidoDAO.crear(pedido);
    }

    public void modificar(Pedido pedido) {
        pedidoDAO.modificar(pedido);
    }

    public void eliminar(int id) {
        pedidoDAO.eliminar(id);
    }
}
