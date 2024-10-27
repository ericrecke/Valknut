package controller.producto;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import model.Cliente;
import model.EnumTipoProducto;
import model.Producto;

import java.math.BigDecimal;

import controller.BaseFormController;
import controller.UtilsController;

public class ProductoFormController extends BaseFormController<Producto> {

    @FXML
    private TextField txtNombre, txtDescripcion, txtPrecio;
    @FXML
    private ComboBox<EnumTipoProducto> cmbTipo;
    
    @Override
    public void initialize(Producto producto) {
        if (producto != null) {
        	this.item = producto;
            txtNombre.setText(item.obtenerNombre());
            txtDescripcion.setText(item.obtenerDescripcion());
            txtPrecio.setText(item.obtenerPrecio().toString());
            cmbTipo.setValue(item.obtenerTipo());
        }
        // Inicializar ComboBox o cualquier dato adicional
    	cmbTipo.getItems().setAll(EnumTipoProducto.values());
    }
    
    @Override
    public void modoVista() {
    	txtNombre.setEditable(false);
    	txtDescripcion.setEditable(false);
    	txtPrecio.setEditable(false);
    	btnGuardar.setVisible(false);
    }


    @Override
    public void guardar() {    	
        // Validación de cada campo
        if (txtNombre.getText() == null || txtNombre.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error","El campo 'Nombre' no puede estar vacío.",null);
            return;
        }

        if (txtDescripcion.getText() == null || txtDescripcion.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error","El campo 'Descripción' no puede estar vacío.",null);
            return;
        }

        if (txtPrecio.getText() == null || txtPrecio.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error","El campo 'Precio' no puede estar vacío.",null);
            return;
        }        

        BigDecimal precio;
        try {
            precio = new BigDecimal(txtPrecio.getText());
            if (precio.compareTo(BigDecimal.ZERO) <= 0) {
                UtilsController.mostrarAlerta("Error","El precio debe ser un valor positivo.",null);
                return;
            }
        } catch (NumberFormatException e) {
            UtilsController.mostrarAlerta("Error","El campo 'Precio' debe ser un número válido.",null);
            return;
        }

        if (cmbTipo.getValue() == null) {
            UtilsController.mostrarAlerta("Error","Debe seleccionar un tipo de producto.",null);
            return;
        }
        
        if (item == null) {
        	item = new Producto(
                    0, // ID provisional
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    precio,
                    cmbTipo.getValue()
            );
            // Lógica para agregar el nuevo producto a la lista
        } else {
        	item.setNombre(txtNombre.getText());
        	item.setDescripcion(txtDescripcion.getText());
        	item.setPrecio(precio);
        	item.setTipo(cmbTipo.getValue());
            // Lógica para actualizar el producto
        }
        guardado = true;
        cerrar();
    }
    
    @Override
    public void cerrar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public Producto getItem() {
    	return item;
    }
    

}
