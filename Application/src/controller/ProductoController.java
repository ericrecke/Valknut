package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Producto;

public class ProductoController {
    @FXML
    private TableView<Producto> tablaProductos;
    
    @FXML
    private TableColumn<Producto, Integer> colId;
    
    @FXML
    private TableColumn<Producto, String> colNombre;
    
    @FXML
    private TableColumn<Producto, String> colEmail;
    
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
        ObservableList<Producto> Productos = FXCollections.observableArrayList(
            new Producto(1, "Juan Pérez", "juan.perez@example.com"),
            new Producto(2, "Ana Gómez", "ana.gomez@example.com")
            // Agregar más Productos según sea necesario
        );
        tablaProductos.setItems(Productos);
    }

    // Métodos para los botones CRUD
    @FXML
    private void crearProducto() {
        // Lógica para crear un nuevo Producto
    }

    @FXML
    private void leerProducto() {
        // Lógica para leer un Producto
    }

    @FXML
    private void actualizarProducto() {
        // Lógica para actualizar un Producto
    }

    @FXML
    private void eliminarProducto() {
        // Lógica para eliminar un Producto
    }
}