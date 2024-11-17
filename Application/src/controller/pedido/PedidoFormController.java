package controller.pedido;

import controller.BaseFormController;
import controller.UtilsController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PedidoFormController extends BaseFormController<Pedido> {

    @FXML
    private TextField txtDireccion, txtNombre, txtApellido, txtLocalidad, txtTelefono, txtTotal;
    @FXML
    private ComboBox<EnumTipoPago> cmbFormaPago;
    @FXML
    private ComboBox<EnumEstadoPedido> cmbEstadoPedido;
    
    @FXML
    private ListView<PedidoProducto> listProductosSeleccionados;
    @FXML
    private TableView<Producto> tablaProductosDisponibles;
    @FXML
    private Button btnGuardar, btnCerrar;

    private Cliente clienteActual;
    private Map<Integer, PedidoProducto> productosSeleccionados = new HashMap<>();
    
    @FXML
    private TableColumn<Producto, String> colNombreProducto;
    @FXML
    private TableColumn<Producto, BigDecimal> colPrecioProducto;
    @FXML
    private TableColumn<Producto, String> colTipoProducto;

    @Override
    public void initialize(Pedido item) {    	
        // Configurar el CellFactory para el ListView de PedidoProducto
        listProductosSeleccionados.setCellFactory(lv -> new ListCell<PedidoProducto>() {
            @Override
            protected void updateItem(PedidoProducto pedidoProducto, boolean empty) {
                super.updateItem(pedidoProducto, empty);
                if (empty || pedidoProducto == null || pedidoProducto.obtenerProducto() == null) {
                    setText(null);
                } else {
                    // Mostrar el nombre del producto desde el objeto PedidoProducto
                    String texto = pedidoProducto.obtenerProducto().obtenerNombre() + " (" + pedidoProducto.obtenerCantidad() + ")";
                    setText(texto);
                }
            }
        });    	
        colNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerNombre()));
        colPrecioProducto.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().obtenerPrecio()));
        colTipoProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTipo().obtenerTipoProducto()));
        
        cmbFormaPago.getItems().setAll(EnumTipoPago.values());
        cmbEstadoPedido.getItems().setAll(EnumEstadoPedido.values());
        cmbEstadoPedido.setValue(EnumEstadoPedido.CREADO);
        
        cargarProductosDisponibles();

        if (item != null) {
            this.item = item;
            cargarDatosPedido(item);
        }
    }

    @Override
    public void modoVista() {
        txtDireccion.setEditable(false);
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtTelefono.setEditable(false);
        cmbFormaPago.setDisable(true);
        cmbEstadoPedido.setDisable(true);
        listProductosSeleccionados.setDisable(true);
        tablaProductosDisponibles.setDisable(true);
        btnGuardar.setVisible(false);
    }

    @Override
    public void guardar() {
        if (clienteActual == null) {
            UtilsController.mostrarAlerta("Error", "Debe buscar y seleccionar un cliente antes de guardar el pedido.", null);
            return;
        }

        Usuario usuarioLogueado = usuarioService.getUsuarioLogueado();
        if (usuarioLogueado == null) {
            UtilsController.mostrarAlerta("Error", "El usuario no existe.", null);
            return;
        }

        if (cmbFormaPago.getValue() == null) {
            UtilsController.mostrarAlerta("Error", "Debe seleccionar una forma de pago.", null);
            return;
        }
        
        // Verificar si la forma de pago es válida (simulación en este caso)
        if (cmbFormaPago.getValue() == null) {
            UtilsController.mostrarAlerta("Error", "Forma de pago no válida.", null);
            return;
        }

        if (cmbEstadoPedido.getValue() == null) {
            UtilsController.mostrarAlerta("Error", "Debe seleccionar un estado.", null);
            return;
        }

        BigDecimal costoTotal = productosSeleccionados.values().stream()
                .map(p -> p.obtenerProducto().obtenerPrecio().multiply(BigDecimal.valueOf(p.obtenerCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Validar que el costo total sea positivo
        if (costoTotal.compareTo(BigDecimal.ZERO) < 0) {
            UtilsController.mostrarAlerta("Error", "El costo total no puede ser negativo.", null);
            return;
        }
        
        txtTotal.setText(costoTotal.toString());        
        
        // Validar que haya productos seleccionados
        if (listProductosSeleccionados.getItems().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "Debe seleccionar al menos un producto para el pedido.", null);
            return;
        }
        
        Date fechaInicio = new Date();
        Date fechaFin = null;

        if (item == null) {
            item = new Pedido(
                0,
                usuarioLogueado,
                clienteActual,
                costoTotal,
                cmbFormaPago.getValue(),
                fechaInicio,
                fechaFin,
                cmbEstadoPedido.getValue()
            );
            item.setProductos(new ArrayList<>(productosSeleccionados.values())); // Establecer productos
            //pedidoService.crear(item);
        } else {
            item.setUsuario(usuarioLogueado);
            item.setCliente(clienteActual);
            item.setCostoTotal(costoTotal);
            item.setFormaPago(cmbFormaPago.getValue());
            item.setFechaInicio(fechaInicio);
            item.setEstado(cmbEstadoPedido.getValue());
            item.setProductos(new ArrayList<>(productosSeleccionados.values())); // Establecer productos
            //pedidoService.modificar(item);
        }

        guardado = true;
        UtilsController.mostrarAlerta("Éxito", "El pedido ha sido guardado exitosamente.", null);
        cerrar();
    }

    @Override
    public void cerrar() {
        Stage stage = (Stage) txtDireccion.getScene().getWindow();
        stage.close();
    }

    @Override
    public Pedido getItem() {
        return item;
    }

    @FXML
    private void buscarCliente() {
        String direccion = txtDireccion.getText();
        if (direccion == null || direccion.trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "Debe ingresar una dirección para buscar el cliente.", null);
            return;
        }

        Optional<Cliente> clienteOpt = clienteService.buscarPorDireccion(direccion);
        if (clienteOpt.isPresent()) {
            clienteActual = clienteOpt.get();
            cargarDatosCliente(clienteActual);
        } else {
            UtilsController.mostrarAlerta("Información", "Cliente no encontrado con la dirección proporcionada.", null);
            limpiarCamposCliente();
        }
    }

    @FXML
    private void crearNuevoCliente() {
        Cliente nuevoCliente = UtilsController.abrirFormularioCliente();
        if (nuevoCliente != null) {
            clienteActual = nuevoCliente;
            cargarDatosCliente(clienteActual);
        }
    }

    @FXML
    private void agregarProducto() {
        Producto productoSeleccionado = tablaProductosDisponibles.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            int idProducto = productoSeleccionado.obtenerId();
            PedidoProducto pedidoProducto = productosSeleccionados.getOrDefault(idProducto, new PedidoProducto(item, productoSeleccionado, 0));
            pedidoProducto.setCantidad(pedidoProducto.obtenerCantidad() + 1);
            productosSeleccionados.put(idProducto, pedidoProducto);
            actualizarListaProductosSeleccionados();
            actualizarCostoTotal();
        }
    }

    @FXML
    private void eliminarProducto() {
        PedidoProducto productoSeleccionado = listProductosSeleccionados.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            int idProducto = productoSeleccionado.obtenerProducto().obtenerId();
            productosSeleccionados.remove(idProducto);
            actualizarListaProductosSeleccionados();
            actualizarCostoTotal();
        }
    }

    private void cargarProductosDisponibles() {
        tablaProductosDisponibles.setItems(FXCollections.observableArrayList(productoService.obtenerTodos()));
        tablaProductosDisponibles.refresh();
    }

    private void actualizarCostoTotal() {
        BigDecimal total = productosSeleccionados.values().stream()
                .map(p -> p.obtenerProducto().obtenerPrecio().multiply(BigDecimal.valueOf(p.obtenerCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        txtTotal.setText(total.toString());
    }

    private void actualizarListaProductosSeleccionados() {
        listProductosSeleccionados.getItems().setAll(productosSeleccionados.values());
    }

    private void cargarDatosPedido(Pedido pedido) {
        clienteActual = pedido.obtenerCliente();
        if (clienteActual != null) {
            cargarDatosCliente(clienteActual);
        }
        cmbFormaPago.setValue(pedido.obtenerFormaPago());
        cmbEstadoPedido.setValue(pedido.obtenerEstado());
        txtTotal.setText(pedido.obtenerCostoTotal().toString());

        // Cargar los productos seleccionados
        productosSeleccionados = pedido.obtenerProductos().stream()
                .collect(Collectors.toMap(p -> p.obtenerProducto().obtenerId(), p -> p));
        actualizarListaProductosSeleccionados();
    }

    private void cargarDatosCliente(Cliente cliente) {
        txtNombre.setText(cliente.obtenerNombre());
        txtApellido.setText(cliente.obtenerApellido());
        txtTelefono.setText(cliente.obtenerTelefono());
        txtLocalidad.setText(cliente.obtenerLocalidad());
    }

    private void limpiarCamposCliente() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtLocalidad.clear();
    }
}
