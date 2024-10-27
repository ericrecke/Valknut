package controller.producto;

import java.math.BigDecimal;
import controller.BaseController;
import controller.UtilsController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import model.Producto;
import model.Cliente;
import model.EnumTipoProducto;

public class ProductoController extends BaseController<Producto> {
       
    @FXML
    private TableColumn<Producto, String> colNombre;
    
    @FXML
    private TableColumn<Producto, String> colDescripcion;
    
    @FXML
    private TableColumn<Producto, String> colPrecio;
  
    @FXML
    private TableColumn<Producto, String> colTipo;
    
    @Override    
    protected void initialize() {
    	view = "/view/ViewProductoForm.fxml";    	
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().obtenerId()).asObject());
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerNombre()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerDescripcion()));
        colPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerPrecio().toString()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTipo().obtenerTipoProducto()));
        // Cargar los datos en la tabla
        cargar();
    }
    
    @Override
    protected void cargar() {
        Items = FXCollections.observableArrayList(productoService.listar());
        tabla.setItems(Items);
        tabla.refresh();
    }
    
    
    @Override
    protected void saveForm(Producto item) {
    	if(item != null) {
    		//Items.set(item.obtenerId() - 1, item);
    		productoService.modificar(item.obtenerId() - 1, item);
    	}
    	else {
    		//Se setea un id segun la cantidad de listados porque es un array y no estamos usando la base todavia.
    		item = form.getItem();
    		int id = Items.size();
    		item.setId(id + 1);
        	//Items.add(item);
    		productoService.guardar(item);
    	}
    }
    

    // Métodos para los botones CRUD
    
    @Override
    protected  void crear() {
    	tituloForm = "Crear Producto";
    	cargarForm(null, true);
    }

    @Override
    protected  void ver() {
    	tituloForm = "Ver Producto";
        Producto item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Producto para ver.", null);
            return;
        }
            cargarForm(item, false);
    }

    @Override
    protected  void modificar() {
    	tituloForm = "Modificar Producto";
        Producto item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Producto para modificar.", null);
            return;
        }
            cargarForm(item, true);
    }

    @Override
    protected  void eliminar() {
        // Lógica para eliminar un Producto
        Producto item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Producto para eliminar.", null);
            return;
        }
        var resultado = UtilsController.mostrarAlerta("Confirmación de Eliminación","Esta acción no se puede deshacer." , "¿Está seguro de que desea eliminar el Producto seleccionado?");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Si el usuario confirma, eliminar el Producto del ArrayList
            Items.remove(item);
            cargar();
        }       
    }

}