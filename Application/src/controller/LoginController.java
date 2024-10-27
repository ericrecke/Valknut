package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Sesion;
import service.UsuarioService;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;


public class LoginController {

	// Crear una instancia de UsuarioService
	private final UsuarioService usuarioService = UsuarioService.getInstancia();	
	
    @FXML
    private TextField mailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;
    
    @FXML
    public void iniciarSesion(ActionEvent event) {
        String mail = mailField.getText();
        String password = passwordField.getText();
        //Si tiene cuenta Owner de Administrador, sino buscar login.
        if (usuarioService.autenticar(mail, password)) {
            System.out.println("Autenticación exitosa. Usuario logueado: " + usuarioService.getUsuarioLogueado().obtenerNombre());
            try {
                Parent mainViewParent = FXMLLoader.load(getClass().getResource("/view/ViewMain.fxml"));
                Scene mainViewScene = new Scene(mainViewParent);

                // Obtener el stage actual a través del evento
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(mainViewScene);
                window.show();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Fallo en la autenticación. Verifique sus credenciales.");
            UtilsController.mostrarAlerta("Error", "Fallo en la autenticación. Verifique sus credenciales.", null);
        }
    }
    
    // Método para validar credenciales contra una base de datos (simulado por ahora)
    private boolean validarCredencialesDesdeBaseDeDatos(String mail, String password) {
        // Aquí iría la lógica para consultar la base de datos y verificar si las credenciales son válidas.
        // Simulación por ahora: devuelve false
        return false;
    }
}
