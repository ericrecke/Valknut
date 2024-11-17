package controller.pedido;

import controller.BaseController;
import controller.UtilsController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import model.Pedido;
import model.PedidoProducto;
import service.PedidoService;
import java.util.Date;

public class PedidoController extends BaseController<Pedido> {

    @FXML
    private TableColumn<Pedido, Integer> colId;
    @FXML
    private TableColumn<Pedido, String> colClienteNombre;
    @FXML
    private TableColumn<Pedido, String> colClienteApellido;
    @FXML
    private TableColumn<Pedido, String> colClienteDireccion;
    @FXML
    private TableColumn<Pedido, String> colCostoTotal;
    @FXML
    private TableColumn<Pedido, String> colFormaPago;
    @FXML
    private TableColumn<Pedido, String> colFechaInicio;
    @FXML
    private TableColumn<Pedido, String> colFechaFin;
    @FXML
    private TableColumn<Pedido, String> colEstado;

    @Override
    protected void initialize() {
        view = "/view/ViewPedidoForm.fxml";
        // Configurar columnas de la tabla
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().obtenerId()).asObject());
        colClienteNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerCliente().obtenerNombre()));
        colClienteApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerCliente().obtenerApellido()));
        colClienteDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerCliente().obtenerDireccion()));
        colCostoTotal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerCostoTotal().toString()));
        colFormaPago.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerFormaPago().obtenerTipoPago()));
        colFechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerFechaInicio().toString()));
        colFechaFin.setCellValueFactory(cellData -> {
            Date fechaFin = cellData.getValue().obtenerFechaFin();
            return new SimpleStringProperty(fechaFin != null ? fechaFin.toString() : "No finalizado");
        });
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerEstado().obtenerEstado()));

        // Cargar los datos en la tabla
        cargar();
    }

    @Override
    protected void cargar() {
        Items = FXCollections.observableArrayList(pedidoService.listar());
        tabla.setItems(Items);
        tabla.refresh();
    }

    @Override
    protected void saveForm(Pedido item) {
        if (item != null) {
            pedidoService.modificar(item);
        } else {
            item = form.getItem();
            pedidoService.crear(item);
        }
        cargar();
    }

    // Métodos para los botones CRUD
    @Override
    protected void crear() {
        tituloForm = "Crear Pedido";
        cargarForm(null, true);
    }

    @Override
    protected void ver() {
        tituloForm = "Ver Pedido";
        Pedido item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
            UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Pedido para ver.", null);
            return;
        }
        cargarForm(item, false);
    }

    @Override
    protected void modificar() {
        tituloForm = "Modificar Pedido";
        Pedido item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
            UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Pedido para modificar.", null);
            return;
        }
        cargarForm(item, true);
    }

    @Override
    protected void eliminar() {
        Pedido item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
            UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Pedido para eliminar.", null);
            return;
        }
        var resultado = UtilsController.mostrarAlerta("Confirmación de Eliminación", "Esta acción no se puede deshacer.", "¿Está seguro de que desea eliminar el Pedido seleccionado?");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            pedidoService.eliminar(item.obtenerId());
            cargar();
        }
    }
}
