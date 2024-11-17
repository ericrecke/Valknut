package controller.usuario;

import controller.BaseController;
import controller.UtilsController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import model.EnumRol;
import model.EnumSexo;
import model.Usuario;
import service.UsuarioService;

public class UsuarioController extends BaseController<Usuario> {   
    @FXML
    private TableColumn<Usuario, String> colNombre;
    
    @FXML
    private TableColumn<Usuario, String> colApellido;
    
    @FXML
    private TableColumn<Usuario, String> colEmail;
    
    @FXML
    private TableColumn<Usuario, String> colPassword;
    
    @FXML
    private TableColumn<Usuario, String> colSexo;
    
    @FXML
    private TableColumn<Usuario, String> colRol;
    
    @Override    
    protected void initialize() {
    	view = "/view/ViewUsuarioForm.fxml";   	
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().obtenerId()).asObject());
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerApellido()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerEmail()));
        colPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerPassword()));
        colSexo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerSexo().obtenerSexo()));
        colRol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerRol().obtenerRol()));
        // Cargar los datos en la tabla
        cargar();
    }
    
    @Override
    protected void cargar() {
        Items = FXCollections.observableArrayList(usuarioService.listar());
        tabla.setItems(Items);
        tabla.refresh();
    }
    
    
    @Override
    protected void saveForm(Usuario item) {
    	if(item != null) {
    		usuarioService.modificar(item);
    	}
    	else {
    		item = form.getItem();
        	usuarioService.crear(item);
    	}
    }
    

    // Métodos para los botones CRUD    
    @Override
    protected  void crear() {
    	tituloForm = "Crear Usuario";
    	cargarForm(null, true);
    }

    @Override
    protected  void ver() {
    	tituloForm = "Ver Usuario";
        Usuario item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Usuario para ver.", null);
            return;
        }
            cargarForm(item, false);
    }

    @Override
    protected  void modificar() {
    	tituloForm = "Modificar Usuario";
        Usuario item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Usuario para modificar.", null);
            return;
        }
            cargarForm(item, true);
    }

    @Override
    protected  void eliminar() {
        // Lógica para eliminar un Usuario
        Usuario item = tabla.getSelectionModel().getSelectedItem();
        if (item == null) {
        	UtilsController.mostrarAlerta("Selección requerida", "Por favor, seleccione un Usuario para eliminar.", null);
            return;
        }
        var resultado = UtilsController.mostrarAlerta("Confirmación de Eliminación","Esta acción no se puede deshacer." , "¿Está seguro de que desea eliminar el Usuario seleccionado?");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            usuarioService.eliminar(item.obtenerId());
            cargar();
        }       
    }
    
}