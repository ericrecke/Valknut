package service.dao;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ClienteDAO extends BaseDAO<Cliente> {

    @Override
    protected String obtenerTabla() {
        return "clientes";
    }

    @Override
    protected Cliente mapear(ResultSet resultSet) throws SQLException {
        return new Cliente(
            resultSet.getInt("id"),                  // ID
            resultSet.getString("nombre"),           // Nombre
            resultSet.getString("apellido"),         // Apellido
            resultSet.getString("telefono"),         // Teléfono
            resultSet.getString("direccion"),        // Dirección
            resultSet.getString("localidad")         // Localidad
        );
    }

    public void crear(Cliente cliente) {
        String query = "INSERT INTO clientes (nombre, apellido, telefono, direccion, localidad) VALUES (?, ?, ?, ?, ?)";
        crear(cliente, query, (statement, item) -> {
            statement.setString(1, item.obtenerNombre());
            statement.setString(2, item.obtenerApellido());
            statement.setString(3, item.obtenerTelefono());
            statement.setString(4, item.obtenerDireccion());
            statement.setString(5, item.obtenerLocalidad());
        });
    }

    public void modificar(Cliente cliente) {
        String query = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ?, direccion = ?, localidad = ? WHERE id = ?";
        modificar(cliente, query, (statement, item) -> {
            statement.setString(1, item.obtenerNombre());
            statement.setString(2, item.obtenerApellido());
            statement.setString(3, item.obtenerTelefono());
            statement.setString(4, item.obtenerDireccion());
            statement.setString(5, item.obtenerLocalidad());
            statement.setInt(6, item.obtenerId());
        });
    }

    public Optional<Cliente> buscarPorDireccion(String direccion) {
        String query = "SELECT * FROM clientes WHERE direccion = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, direccion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapear(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
