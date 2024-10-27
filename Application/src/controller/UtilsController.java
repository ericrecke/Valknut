package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;

import java.io.IOException;
import java.util.Optional;

import controller.cliente.ClienteFormController;

public final class UtilsController {

    // Constructor privado para evitar instanciación
    private UtilsController() {
    }

    public static Optional<ButtonType> mostrarAlerta(String titulo, String mensaje, String header) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.setContentText(mensaje);
        return alerta.showAndWait();
    }
    
    // Método para abrir el formulario de Cliente
    public static Cliente abrirFormularioCliente() {
        try {
            // Cargar el archivo FXML del formulario de Cliente
            FXMLLoader loader = new FXMLLoader(UtilsController.class.getResource("/view/ViewClienteForm.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del formulario
            ClienteFormController clienteFormController = loader.getController();

            // Configurar la ventana del formulario
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nuevo Cliente");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Verificar si se guardó el cliente y retornarlo
            if (clienteFormController.isGuardado()) {
                return clienteFormController.getItem();
            }

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir el formulario de Cliente.", null);
        }
        return null; // Retorna null si no se guardó un cliente
    }

    // Aca irian metodos a futuro que se reutilizen.
}
