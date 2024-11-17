package service.dao;

import model.EnumRol;
import model.EnumSexo;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioDAO extends BaseDAO<Usuario> {

    @Override
    protected String obtenerTabla() {
        return "usuarios";
    }

    @Override
    protected Usuario mapear(ResultSet resultSet) throws SQLException {
        return new Usuario(
            resultSet.getInt("id"),
            resultSet.getString("nombre"),
            resultSet.getString("apellido"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            EnumSexo.valueOf(resultSet.getString("sexo").trim().toUpperCase()),
            EnumRol.valueOf(resultSet.getString("rol").trim().toUpperCase())
        );
    }
    
    @Override
    public Optional<Usuario> ver(int id) {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
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


    public void crear(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombre, apellido, email, password, sexo, rol) VALUES (?, ?, ?, ?, ?, ?)";
        crear(usuario, query, (statement, item) -> {
            statement.setString(1, item.obtenerNombre());
            statement.setString(2, item.obtenerApellido());
            statement.setString(3, item.obtenerEmail());
            statement.setString(4, item.obtenerPassword());
            statement.setString(5, item.obtenerSexo().name());
            statement.setString(6, item.obtenerRol().name());
        });
    }

    public void modificar(Usuario usuario) {
        String query = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, password = ?, sexo = ?, rol = ? WHERE id = ?";
        modificar(usuario, query, (statement, item) -> {
            statement.setString(1, item.obtenerNombre());
            statement.setString(2, item.obtenerApellido());
            statement.setString(3, item.obtenerEmail());
            statement.setString(4, item.obtenerPassword());
            statement.setString(5, item.obtenerSexo().name());
            statement.setString(6, item.obtenerRol().name());
            statement.setInt(7, item.obtenerId());
        });
    }
}
