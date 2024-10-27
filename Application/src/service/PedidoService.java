package service;

import model.Pedido;
import model.PedidoProducto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PedidoService extends BaseService<Pedido> {
    private static PedidoService instancia;
    private final List<PedidoProducto> pedidoProductos = new ArrayList<>();

    public static PedidoService getInstancia() {
        if (instancia == null) {
            instancia = new PedidoService();
        }
        return instancia;
    }
    @Override
    public Optional<Pedido> buscarPorId(int id) {
        return dataStore.stream().filter(pedido -> pedido.obtenerId() == id).findFirst();
    }
    
    // Método para guardar o actualizar la relación PedidoProducto
    public void guardarPedidoProducto(Pedido pedido, PedidoProducto pedidoProducto) {
        // Si ya existe una relación PedidoProducto para este pedido y producto, se actualiza
        PedidoProducto existente = pedidoProductos.stream()
                .filter(pp -> pp.obtenerPedido().obtenerId() == pedido.obtenerId() && 
                              pp.obtenerProducto().obtenerId() == pedidoProducto.obtenerProducto().obtenerId())
                .findFirst()
                .orElse(null);

        if (existente != null) {
            // Actualizar la cantidad del producto en el pedido
            existente.setCantidad(pedidoProducto.obtenerCantidad());
        } else {
            // Si no existe, se añade una nueva relación
            pedidoProductos.add(pedidoProducto);
        }
    }

    // Método para obtener todos los productos asociados a un pedido específico
    public List<PedidoProducto> obtenerProductosPorPedido(Pedido pedido) {
        return pedidoProductos.stream()
                .filter(pp -> pp.obtenerPedido().obtenerId() == pedido.obtenerId())
                .collect(Collectors.toList());
    }
}
