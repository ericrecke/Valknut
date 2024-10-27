package controller.cliente;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import model.Cliente;
import model.Usuario;
import controller.BaseController;
import controller.UtilsController;

public class ClienteController extends BaseController<Cliente> {
	   
    @FXML
    private TableColumn<Cliente, String> colNombre;    
    @FXML
    private TableColumn<Cliente, String> colApellido;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    @FXML
    private TableColumn<Cliente, String> colLocalidad;
    
    @Override    
    protected void initialize() {
    	view = "/view/ViewClienteForm.fxml";    	
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().obtenerId()).asObject());
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerApellido()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTelefono()));
        colDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerDireccion()));
        colLocalidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerLocalidad()));        
        // Cargar los datos en la tabla    	
        cargar();
    }

    @Override
    protected void cargar() {
        Items = FXCollections.observableArrayList(clienteService.listar());
        tabla.setItems(Items);
        tabla.refresh();
    }
    
    
    @Override
    protected void saveForm(Cliente item) {
    	if(item != null) {
    		//Items.set(item.obtenerId() - 1, item);
    		clienteService.modificar(item.obtenerId() - 1, item);
    	}
    	else {
    		//Se setea un id segun la cantidad de listados porque es un array y no estamos usando la base todavia.
    		item = form.getItem();
    		int id = Items.size();
    		item.setId(id + 1);
        	//Items.add(item);
    		clienteService.guardar(item);
    	}
    }

    // Métodos para los botones CRUD
    
    @Override
    protected  void crear() {
    	tituloForm = "Crear Cliente";
    	cargarForm(null, true);
    }

    @Override
    protected  void ver() {
    	tituloForm = "Ver Cliente";
        Cliente item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un cliente para ver.", null);
            return;
        }
            cargarForm(item, false);
    }

    @Override
    protected  void modificar() {
    	tituloForm = "Modificar Cliente";
        Cliente item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un cliente para modificar.", null);
            return;
        }
            cargarForm(item, true);
    }

    @Override
    protected  void eliminar() {
        // Lógica para eliminar un Cliente
        Cliente item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un cliente para eliminar.", null);
            return;
        }
        var resultado = UtilsController.mostrarAlerta("Confirmación de Eliminación","Esta acción no se puede deshacer." , "¿Está seguro de que desea eliminar el cliente seleccionado?");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Si el usuario confirma, eliminar el cliente del ArrayList
            Items.remove(item);
            cargar();
        }       
    }
}