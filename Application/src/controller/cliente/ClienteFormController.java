package controller.cliente;

import controller.BaseFormController;
import controller.UtilsController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;

public class ClienteFormController extends BaseFormController<Cliente> {

    @FXML
    private TextField txtNombre, txtApellido, txtTelefono, txtDireccion, txtLocalidad;
    

    @Override
    public void initialize(Cliente item) {
        if (item != null) {
        	this.item = item;
            txtNombre.setText(item.obtenerNombre());
            txtApellido.setText(item.obtenerApellido());
            txtTelefono.setText(item.obtenerTelefono());
            txtDireccion.setText(item.obtenerDireccion());
            txtLocalidad.setText(item.obtenerLocalidad());
        }        
    }

    @Override
    public void modoVista() {
        	txtNombre.setEditable(false);
        	txtApellido.setEditable(false);
        	txtTelefono.setEditable(false);
        	txtDireccion.setEditable(false);
        	txtLocalidad.setEditable(false);
        	btnGuardar.setVisible(false);
    }
    
    @Override
    public void guardar() {
    	
        // Validaciones de campos antes de guardar
        if (txtNombre.getText() == null || txtNombre.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Nombre' no puede estar vacío.", null);
            return;
        }
        
        if (txtApellido.getText() == null || txtApellido.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Apellido' no puede estar vacío.", null);
            return;
        }

        if (txtTelefono.getText() == null || txtTelefono.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Teléfono' no puede estar vacío.", null);
            return;
        }

        if (txtDireccion.getText() == null || txtDireccion.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Dirección' no puede estar vacío.", null);
            return;
        }

        if (txtLocalidad.getText() == null || txtLocalidad.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Localidad' no puede estar vacío.", null);
            return;
        }
    	
        if (item == null) {
        	item = new Cliente(
                    0, // ID provisional
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtTelefono.getText(),
                    txtDireccion.getText(),
                    txtLocalidad.getText()
            );
            // Lógica para agregar el nuevo cliente a la lista
        } else {
        	item.setNombre(txtNombre.getText());
            item.setApellido(txtApellido.getText());
            item.setTelefono(txtTelefono.getText());
            item.setDireccion(txtDireccion.getText());
            item.setLocalidad(txtLocalidad.getText());
            // Lógica para actualizar el cliente
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
    public Cliente getItem() {
    	return item;
    }
}
