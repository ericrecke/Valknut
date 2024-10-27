package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;
import model.EnumRol;
import service.UsuarioService;

public class MainController {
	
    protected final UsuarioService usuarioService = UsuarioService.getInstancia();
    
    @FXML
    private Menu menuUsuarios;
    
    @FXML
    private BorderPane mainContent; // Referencia al contenedor central

    @FXML
    private void initialize() {
        // Cargar la vista de Pedidos por defecto
        handlePedido();
        var user = usuarioService.getUsuarioLogueado();
        if(user.obtenerRol() != EnumRol.ADMINISTRADOR)
        	menuUsuarios.setVisible(false);
        else
        	menuUsuarios.setVisible(true);
        
    }
    
    // Manejar el clic en el menú de Usuarios
    @FXML
    private void handleUsuario() {
        loadView("/view/ViewUsuario.fxml");
    }

    @FXML
    private void handleCliente() {
        loadView("/view/ViewCliente.fxml");
    }

    @FXML
    private void handleProducto() {
        loadView("/view/ViewProducto.fxml");
    }

    @FXML
    private void handlePedido() {
        loadView("/view/ViewPedido.fxml");
    }
 
    @FXML
    private void handleReporte() {
    	showAlert("En Desarrollo", "Contenido en desarrollo para nuevas versiones.");
        //loadView("/view/ViewReporte.fxml");
    }

    @FXML
    private void handleSalir() {
        System.exit(0);
    }

    // Cargar dinámicamente un FXML en la sección central
    private void loadView(String fxmlPath) {
        try {
            // Cargar el FXML de la vista solicitada
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Pane view = loader.load();

            // Colocar la vista en el centro del BorderPane
            mainContent.setCenter(view);
        } catch (Exception e) {
            showAlert("Error", "No se pudo cargar la vista: " + fxmlPath);
            e.printStackTrace();
        }
    }

    // Mostrar una alerta en caso de error
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}