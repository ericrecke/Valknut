package controller.usuario;

import controller.BaseFormController;
import controller.UtilsController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Usuario;
import service.UsuarioService;
import model.EnumRol;
import model.EnumSexo;

public class UsuarioFormController extends BaseFormController<Usuario> {
	
    @FXML
    private TextField txtNombre, txtApellido, txtEmail;
    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private ComboBox<EnumRol> cmbRol;    
    @FXML
    private ComboBox<EnumSexo> cmbSexo;
    
    @Override
    public void initialize(Usuario usuario) {
        if (usuario != null) {
            this.item = usuario;
            txtNombre.setText(item.obtenerNombre());
            txtApellido.setText(item.obtenerApellido());
            txtEmail.setText(item.obtenerEmail());
            txtPassword.setText(item.obtenerPassword());
            cmbRol.setValue(item.obtenerRol());
            cmbSexo.setValue(item.obtenerSexo());
        }
        // Inicializa el ComboBox con los valores de EnumRolUsuario
        cmbRol.getItems().setAll(EnumRol.values());
        cmbSexo.getItems().setAll(EnumSexo.values());
    }

    @Override
    public void modoVista() {
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtEmail.setEditable(false);
        txtPassword.setEditable(false);
        cmbRol.setDisable(true);
        cmbSexo.setDisable(true);
        btnGuardar.setVisible(false);
    }

    @Override
    public void guardar() {
        // Validación del nombre
        if (txtNombre.getText() == null || txtNombre.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Nombre' es obligatorio.", null);
            return;
        }
        
        // Validación del apellido
        if (txtApellido.getText() == null || txtApellido.getText().trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Apellido' es obligatorio.", null);
            return;
        }
        
        // Validación del email
        String email = txtEmail.getText();
        if (email == null || email.trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Email' es obligatorio.", null);
            return;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            UtilsController.mostrarAlerta("Error", "El campo 'Email' no tiene un formato válido.", null);
            return;
        }

        // Validación de la contraseña
        String password = txtPassword.getText();
        if (password == null || password.trim().isEmpty()) {
            UtilsController.mostrarAlerta("Error", "El campo 'Password' es obligatorio.", null);
            return;
        }
        if (password.length() < 6) {
            UtilsController.mostrarAlerta("Error", "La contraseña debe tener al menos 6 caracteres.", null);
            return;
        }

        if (cmbRol.getValue() == null) {
            UtilsController.mostrarAlerta("Error", "Debe seleccionar un rol.", null);
            return;
        }
        
        if (cmbSexo.getValue() == null) {
            UtilsController.mostrarAlerta("Error", "Debe seleccionar un sexo.", null);
            return;
        }

        // Si todas las validaciones pasan, guarda el usuario
        if (item == null) {
            item = new Usuario(
                    0, // ID provisional
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtEmail.getText(),
                    txtPassword.getText(),
                    cmbSexo.getValue(),
                    cmbRol.getValue()
            );
            // Lógica para agregar el nuevo usuario a la lista
        } else {
            item.setNombre(txtNombre.getText());
            item.setApellido(txtApellido.getText());
            item.setEmail(txtEmail.getText());
            item.setPassword(txtPassword.getText());
            item.setSexo(cmbSexo.getValue());
            item.setRol(cmbRol.getValue());
            // Lógica para actualizar el usuario
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
    public Usuario getItem() {
        return item;
    }
}
