module ValknutApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
    opens controller to javafx.fxml;  // Esto es necesario para que FXMLLoader pueda acceder al paquete controller
    exports controller;
    //Controllers por individual.
    opens controller.producto to javafx.fxml;  // Esto es necesario para que FXMLLoader pueda acceder al paquete controller
    exports controller.producto;
    opens controller.cliente to javafx.fxml;  // Esto es necesario para que FXMLLoader pueda acceder al paquete controller
    exports controller.cliente;
    opens controller.pedido to javafx.fxml;  // Esto es necesario para que FXMLLoader pueda acceder al paquete controller
    exports controller.pedido;
    opens controller.usuario to javafx.fxml;  // Esto es necesario para que FXMLLoader pueda acceder al paquete controller
    exports controller.usuario;
	opens application to javafx.graphics, javafx.fxml;
	exports application;
}
