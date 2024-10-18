package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Pedido;

public class PedidoController {
    @FXML
    private TableView<Pedido> tablaPedidos;
    
    @FXML
    private TableColumn<Pedido, Integer> colId;
    
    @FXML
    private TableColumn<Pedido, String> colNombre;
    
    @FXML
    private TableColumn<Pedido, String> colEmail;
    
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
        ObservableList<Pedido> Pedidos = FXCollections.observableArrayList(
            new Pedido(1, "Juan Pérez", "juan.perez@example.com"),
            new Pedido(2, "Ana Gómez", "ana.gomez@example.com")
            // Agregar más Pedidos según sea necesario
        );
        tablaPedidos.setItems(Pedidos);
    }

    // Métodos para los botones CRUD
    @FXML
    private void crearPedido() {
        // Lógica para crear un nuevo Pedido
    }

    @FXML
    private void leerPedido() {
        // Lógica para leer un Pedido
    }

    @FXML
    private void actualizarPedido() {
        // Lógica para actualizar un Pedido
    }

    @FXML
    private void eliminarPedido() {
        // Lógica para eliminar un Pedido
    }
}