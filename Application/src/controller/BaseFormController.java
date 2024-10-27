package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import service.ClienteService;
import service.PedidoService;
import service.ProductoService;
import service.UsuarioService;

public abstract class BaseFormController<T> {

	public String titulo;
    public Button btnGuardar,btnCerrar;
    public Boolean modoVisible = false;
    
    //Implementamos los servicios en el base para reutilizarlos en todos.
    protected final ClienteService clienteService = ClienteService.getInstancia();
    protected final PedidoService pedidoService = PedidoService.getInstancia();
    protected final UsuarioService usuarioService = UsuarioService.getInstancia();
    protected final ProductoService productoService = ProductoService.getInstancia();

    
    public boolean isGuardado() {
        return guardado;
    }
	
    protected boolean guardado = false;    
    
    protected T item;       
	
    @FXML
    public abstract void initialize(T item);

    public abstract void modoVista();
    
    /**
     * Método para guardar los datos en el formulario.
     * Si es nuevo, crea una nueva instancia; si es existente, actualiza sus datos.
     */
    @FXML
    public abstract void guardar();

    /**
     * Método para cerrar el formulario.
     */
    @FXML
    public abstract void cerrar();
    
    /**
     * Método para obtener el item.
     */
    public abstract T getItem();    
}