package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Cliente;

public class ClienteController {
    @FXML
    private TableView<Cliente> tablaClientes;
    
    @FXML
    private TableColumn<Cliente, Integer> colId;
    
    @FXML
    private TableColumn<Cliente, String> colNombre;
    
    @FXML
    private TableColumn<Cliente, String> colEmail;
    
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
        ObservableList<Cliente> Clientes = FXCollections.observableArrayList(
            new Cliente(1, "Juan Pérez", "juan.perez@example.com"),
            new Cliente(2, "Ana Gómez", "ana.gomez@example.com")
            // Agregar más Clientes según sea necesario
        );
        tablaClientes.setItems(Clientes);
    }

    // Métodos para los botones CRUD
    @FXML
    private void crearCliente() {
        // Lógica para crear un nuevo Cliente
    }

    @FXML
    private void leerCliente() {
        // Lógica para leer un Cliente
    }

    @FXML
    private void actualizarCliente() {
        // Lógica para actualizar un Cliente
    }

    @FXML
    private void eliminarCliente() {
        // Lógica para eliminar un Cliente
    }
}