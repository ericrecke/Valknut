package application;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML desde la carpeta "view"
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewLogin.fxml"));
            // Crear la escena con la vista cargada
            Scene scene = new Scene(root);
            
            // Configurar la escena en el escenario principal (primaryStage)
            primaryStage.setScene(scene);
            primaryStage.setTitle("Valknut Pizzería - Gestión de Pedidos"); // Título de la ventana
            primaryStage.setResizable(false);
            primaryStage.show(); // Mostrar la ventana
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}