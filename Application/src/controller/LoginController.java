package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;
import service.UsuarioService;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class LoginController {

    // Instancia del UsuarioService
    private final UsuarioService usuarioService = UsuarioService.getInstancia();	

    @FXML
    private TextField mailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void iniciarSesion(ActionEvent event) {
        String mail = mailField.getText().trim();
        String password = passwordField.getText().trim();

        if (mail.isEmpty() || password.isEmpty()) {
            mostrarError("El correo y la contraseña no pueden estar vacíos.");
            return;
        }

        // Autenticar usuario
        if (usuarioService.autenticar(mail, password)) {
            Usuario usuarioLogueado = usuarioService.getUsuarioLogueado();
            System.out.println("Autenticación exitosa. Usuario logueado: " + usuarioLogueado.obtenerNombre());

            // Cargar la vista principal
            cargarVistaPrincipal(event);
        } else {
            mostrarError("Fallo en la autenticación. Verifique sus credenciales.");
        }
    }

    private void cargarVistaPrincipal(ActionEvent event) {
        try {
            // Cargar la vista principal
            Parent mainViewParent = FXMLLoader.load(getClass().getResource("/view/ViewMain.fxml"));
            Scene mainViewScene = new Scene(mainViewParent);

            // Obtener el stage actual desde el evento
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mainViewScene);
            window.show();
        } catch (IOException e) {
            mostrarError("Ocurrió un error al cargar la vista principal.");
            e.printStackTrace();
        }
    }

    private void mostrarError(String mensaje) {
        errorLabel.setText(mensaje);
        errorLabel.setVisible(true);
        System.out.println(mensaje); // Para depuración
    }
}
