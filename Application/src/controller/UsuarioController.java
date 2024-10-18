package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Usuario;

public class UsuarioController {
    @FXML
    private TableView<Usuario> tablaUsuarios;
    
    @FXML
    private TableColumn<Usuario, Integer> colId;
    
    @FXML
    private TableColumn<Usuario, String> colNombre;
    
    @FXML
    private TableColumn<Usuario, String> colEmail;
    
    @FXML
    private void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        // Cargar los datos en la tabla
        cargarDatos();
    }

    private void cargarDatos() {
       // Aquí agregar lógica para cargar datos desde una base de datos o lista
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList(
            new Usuario(1, "Juan Pérez", "juan.perez@example.com"),
            new Usuario(2, "Ana Gómez", "ana.gomez@example.com")
            // Agregar más usuarios según sea necesario
        );
        tablaUsuarios.setItems(usuarios);
    }

    // Métodos para los botones CRUD
    @FXML
    private void crearUsuario() {
        // Lógica para crear un nuevo usuario
    }

    @FXML
    private void leerUsuario() {
        // Lógica para leer un usuario
    }

    @FXML
    private void actualizarUsuario() {
        // Lógica para actualizar un usuario
    }

    @FXML
    private void eliminarUsuario() {
        // Lógica para eliminar un usuario
    }
}