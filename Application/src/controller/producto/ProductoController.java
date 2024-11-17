package controller.producto;

import controller.BaseController;
import controller.UtilsController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Producto;
import service.ProductoService;

public class ProductoController extends BaseController<Producto> {

    @FXML
    private TableView<Producto> tabla;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colDescripcion;

    @FXML
    private TableColumn<Producto, String> colPrecio;

    @FXML
    private TableColumn<Producto, String> colTipo;

    private final ProductoService productoService = ProductoService.getInstancia(); // Singleton de ProductoService

    @Override
    protected void initialize() {
        view = "/view/ViewProductoForm.fxml";
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().obtenerId()).asObject());
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerNombre()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerDescripcion()));
        colPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerPrecio().toString()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTipo().name()));

        // Cargar los datos en la tabla
        cargar();
    }

    @Override
    protected void cargar() {
        // Obtener datos desde la base de datos a través del servicio
        Items = FXCollections.observableArrayList(productoService.obtenerTodos());
        tabla.setItems(Items);
        tabla.refresh();
    }

    @Override
    protected void saveForm(Producto item) {
        if (item != null) { // Actualizar un producto existente
            productoService.actualizarProducto(item);
        } else { // Crear un nuevo producto
            item = form.getItem();
            productoService.agregarProducto(item);
        }
        cargar(); // Refrescar la tabla
    }

    @Override
    protected void crear() {
        tituloForm = "Crear Producto";
        cargarForm(null, true);
    }

    @Override
    protected void ver() {
        tituloForm = "Ver Producto";
        Producto item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
            UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Producto para ver.", null);
            return;
        }
        cargarForm(item, false);
    }

    @Override
    protected void modificar() {
        tituloForm = "Modificar Producto";
        Producto item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
            UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Producto para modificar.", null);
            return;
        }
        cargarForm(item, true);
    }

    @Override
    protected void eliminar() {
        Producto item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
            UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Producto para eliminar.", null);
            return;
        }
        var resultado = UtilsController.mostrarAlerta("Confirmación de Eliminación", "Esta acción no se puede deshacer.", "¿Está seguro de que desea eliminar el Producto seleccionado?");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Eliminar producto desde la base de datos
            productoService.eliminarProducto(item.obtenerId());
            cargar(); // Refrescar la tabla
        }
    }
}
