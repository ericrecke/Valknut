module ValknutApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
    opens controller to javafx.fxml;  // Esto es necesario para que FXMLLoader pueda acceder al paquete controller
    exports controller;
	opens application to javafx.graphics, javafx.fxml;
	exports application;
}
