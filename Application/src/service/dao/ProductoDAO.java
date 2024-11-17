package service.dao;

import model.EnumTipoProducto;
import model.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO extends BaseDAO<Producto> {

    @Override
    protected String obtenerTabla() {
        return "productos";
    }

    @Override
    protected Producto mapear(ResultSet resultSet) throws SQLException {
        return new Producto(
            resultSet.getInt("id"),
            resultSet.getString("nombre"),
            resultSet.getString("descripcion"),
            resultSet.getBigDecimal("precio"),
            EnumTipoProducto.valueOf(resultSet.getString("tipo").trim().toUpperCase())
        );
    }

    public void crear(Producto producto) {
        String query = "INSERT INTO productos (nombre, descripcion, precio, tipo) VALUES (?, ?, ?, ?)";
        crear(producto, query, (statement, item) -> {
            statement.setString(1, item.obtenerNombre());
            statement.setString(2, item.obtenerDescripcion());
            statement.setBigDecimal(3, item.obtenerPrecio());
            statement.setString(4, item.obtenerTipo().name());
        });
    }

    public void modificar(Producto producto) {
        String query = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, tipo = ? WHERE id = ?";
        modificar(producto, query, (statement, item) -> {
            statement.setString(1, item.obtenerNombre());
            statement.setString(2, item.obtenerDescripcion());
            statement.setBigDecimal(3, item.obtenerPrecio());
            statement.setString(4, item.obtenerTipo().name());
            statement.setInt(5, item.obtenerId());
        });
    }
}
