package service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseDAO<T> {
    
    protected abstract String obtenerTabla(); // Nombre de la tabla
    protected abstract T mapear(ResultSet resultSet) throws SQLException; // Mapea ResultSet al modelo

    public List<T> listar() {
        List<T> items = new ArrayList<>();
        String query = "SELECT * FROM " + obtenerTabla();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                items.add(mapear(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public void crear(T item, String query, Parametrizador<T> parametrizador) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            parametrizador.parametrizar(statement, item);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificar(T item, String query, Parametrizador<T> parametrizador) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            parametrizador.parametrizar(statement, item);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM " + obtenerTabla() + " WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<T> ver(int id) {
        String query = "SELECT * FROM " + obtenerTabla() + " WHERE id = ?";
        T item = null;

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

    @FunctionalInterface
    public interface Parametrizador<T> {
        void parametrizar(PreparedStatement statement, T item) throws SQLException;
    }
}
