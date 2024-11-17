package service.dao;

import model.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends BaseDAO<Pedido> {

    @Override
    protected String obtenerTabla() {
        return "pedidos";
    }

    @Override
    protected Pedido mapear(ResultSet resultSet) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        Usuario usuario = usuarioDAO.ver(resultSet.getInt("id_usuario")).orElse(null);
        Cliente cliente = clienteDAO.ver(resultSet.getInt("id_cliente")).orElse(null);

        Pedido pedido = new Pedido(
            resultSet.getInt("id"),
            usuario,
            cliente,
            resultSet.getBigDecimal("costoTotal"),
            EnumTipoPago.valueOf(resultSet.getString("formaPago").trim().toUpperCase()),
            resultSet.getDate("fechaInicio"),
            resultSet.getDate("fechaFin"),
            EnumEstadoPedido.valueOf(resultSet.getString("estado").trim().toUpperCase())
        );

        // Cargar productos asociados
        pedido.setProductos(obtenerProductosPorPedido(pedido.obtenerId()));
        return pedido;
    }

    public void crear(Pedido pedido) {
        String query = "INSERT INTO pedidos (id_usuario, id_cliente, costoTotal, formaPago, fechaInicio, fechaFin, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, pedido.obtenerUsuario().obtenerId());
            statement.setInt(2, pedido.obtenerCliente().obtenerId());
            statement.setBigDecimal(3, pedido.obtenerCostoTotal());
            statement.setString(4, pedido.obtenerFormaPago().name());
            statement.setDate(5, new java.sql.Date(pedido.obtenerFechaInicio().getTime()));
            statement.setDate(6, pedido.obtenerFechaFin() != null ? new java.sql.Date(pedido.obtenerFechaFin().getTime()) : null);
            statement.setString(7, pedido.obtenerEstado().name());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No se pudo insertar el Pedido, no se generaron filas afectadas.");
            }

            // Obtener el ID generado
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pedido.setId(generatedKeys.getInt(1)); // Establecer el ID generado en el objeto Pedido
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el Pedido.");
                }
            }

            // Guardar los productos asociados al Pedido
            guardarProductosDePedido(pedido);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void modificar(Pedido pedido) {
        String query = "UPDATE pedidos SET id_usuario = ?, id_cliente = ?, costoTotal = ?, formaPago = ?, fechaInicio = ?, fechaFin = ?, estado = ? WHERE id = ?";
        modificar(pedido, query, (statement, item) -> {
            statement.setInt(1, item.obtenerUsuario().obtenerId());
            statement.setInt(2, item.obtenerCliente().obtenerId());
            statement.setBigDecimal(3, item.obtenerCostoTotal());
            statement.setString(4, item.obtenerFormaPago().name());
            statement.setDate(5, new java.sql.Date(item.obtenerFechaInicio().getTime()));
            statement.setDate(6, item.obtenerFechaFin() != null ? new java.sql.Date(item.obtenerFechaFin().getTime()) : null);
            statement.setString(7, item.obtenerEstado().name());
            statement.setInt(8, item.obtenerId());
        });

        eliminarProductosDePedido(pedido.obtenerId());
        guardarProductosDePedido(pedido);
    }
    
    @Override
    public void eliminar(int idPedido) {
        try (Connection connection = DBConnection.getConnection()) {
            // Eliminar los productos asociados al Pedido
            String eliminarProductosQuery = "DELETE FROM PedidoProducto WHERE id_pedido = ?";
            try (PreparedStatement eliminarProductosStatement = connection.prepareStatement(eliminarProductosQuery)) {
                eliminarProductosStatement.setInt(1, idPedido);
                eliminarProductosStatement.executeUpdate();
            }

            // Eliminar el Pedido
            String eliminarPedidoQuery = "DELETE FROM pedidos WHERE id = ?";
            try (PreparedStatement eliminarPedidoStatement = connection.prepareStatement(eliminarPedidoQuery)) {
                eliminarPedidoStatement.setInt(1, idPedido);
                eliminarPedidoStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void guardarProductosDePedido(Pedido pedido) {
        if (pedido.obtenerId() <= 0) {
            throw new IllegalArgumentException("El Pedido debe tener un ID válido antes de asociar productos.");
        }

        List<PedidoProducto> productos = pedido.obtenerProductos();
        if (productos == null || productos.isEmpty()) {
            return; // No hay productos que guardar
        }

        String query = "INSERT INTO PedidoProducto (id_pedido, id_producto, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(query)) {
            for (PedidoProducto pedidoProducto : productos) {
                statement.setInt(1, pedido.obtenerId());
                statement.setInt(2, pedidoProducto.obtenerProducto().obtenerId());
                statement.setInt(3, pedidoProducto.obtenerCantidad());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void eliminarProductosDePedido(int idPedido) {
        String query = "DELETE FROM PedidoProducto WHERE id_pedido = ?";
        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(query)) {
            statement.setInt(1, idPedido);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<PedidoProducto> obtenerProductosPorPedido(int idPedido) {
        String query = "SELECT * FROM PedidoProducto WHERE id_pedido = ?";
        List<PedidoProducto> productos = new ArrayList<>();

        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(query)) {
            statement.setInt(1, idPedido);
            try (ResultSet resultSet = statement.executeQuery()) {
                ProductoDAO productoDAO = new ProductoDAO();
                while (resultSet.next()) {
                    Producto producto = productoDAO.ver(resultSet.getInt("id_producto")).orElse(null);
                    if (producto != null) {
                        productos.add(new PedidoProducto(
                            null, // Pedido se asigna después
                            producto,
                            resultSet.getInt("cantidad")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
