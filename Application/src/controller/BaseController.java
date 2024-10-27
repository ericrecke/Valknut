package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.EntityModel;
import service.ClienteService;
import service.PedidoService;
import service.ProductoService;
import service.UsuarioService;

public abstract class BaseController<T> {
	
    public TableView<T> tabla;
    
    public String tituloForm;
    
    protected String view = "";
    
    public BaseFormController<T> form;
    
    protected FXMLLoader loader;
    
    protected Parent root;
    
    protected Alert alerta;
    
    //Implementamos los servicios en el base para reutilizarlos en todos.
    protected final ClienteService clienteService = ClienteService.getInstancia();
    protected final PedidoService pedidoService = PedidoService.getInstancia();
    protected final UsuarioService usuarioService = UsuarioService.getInstancia();
    protected final ProductoService productoService = ProductoService.getInstancia();

    @FXML
    protected TableColumn<T, Integer> colId;    
    
    protected ObservableList<T> Items = FXCollections.observableArrayList();
    
    @FXML
    protected abstract void initialize();
    
    // Métodos CRUD abstractos, cada controlador deberá implementar su lógica específica.
    @FXML
    protected abstract void crear();

    @FXML
    protected abstract void ver();

    @FXML
    protected abstract void modificar();

    @FXML
    protected abstract void eliminar();
    
    protected abstract void cargar();
    
    //Por ahora va aca, despues con los cambios en BD se modificara.
    protected abstract void saveForm(T item);

    protected void initializeForm() {
    	try {
            // Cargar el archivo FXML
            loader = new FXMLLoader(getClass().getResource(view));
            root = loader.load();            
            // Obtener el controlador del formulario
            form = loader.getController();
    	}
    	 catch (IOException e) {
             e.printStackTrace();
         }
    }
    
    protected void cargarForm(T item, boolean editable) {    	
    	initializeForm();
        // Pasar el producto al controlador, si es necesario (modo edición)
        form.initialize(item != null ? item : null);
        
        if(!editable)
        	form.modoVista();     
        // Crear una nueva ventana para el formulario
        Stage stage = new Stage();
        stage.setTitle(tituloForm);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);  // Bloquea la ventana principal hasta cerrar esta
        stage.showAndWait();
        
        // Si el producto se creó o modificó, actualizar la lista de productos
        if (form.isGuardado()) {
        	//Este metodo esta agregado hasta el tp4, donde se hace la mejora a Base de datos.
        	saveForm(item);
        	cargar();
        }
        stage.close();
    }   
    
}