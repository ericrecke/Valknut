package service;

import model.EnumRol;
import model.EnumSexo;
import model.Usuario;

import java.util.Optional;

public class UsuarioService extends BaseService<Usuario> {
    private Usuario usuarioLogueado;
    private static UsuarioService instancia;

    public static UsuarioService getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioService();
        }
        return instancia;
    }
    
	public UsuarioService(){
		//Agregamos valores hardcoded por ahora.
	    dataStore.add(new Usuario(1, "Juan", "Gutierrez", "juan.perez@example.com", "password123", EnumSexo.MASCULINO, EnumRol.EMPLEADO ));
	    dataStore.add(new Usuario(2, "Ana", "Gomez", "ana.gomez@example.com", "password123", EnumSexo.FEMENINO, EnumRol.EMPLEADO ));  
	    dataStore.add(new Usuario(3, "Admin", "Valknut", "admin@valknut.com", "admin123", EnumSexo.MASCULINO, EnumRol.ADMINISTRADOR ));      
	    dataStore.add(new Usuario(4, "test", "test", "test", "test", EnumSexo.MASCULINO, EnumRol.ADMINISTRADOR ));      
	}
    
    // Método para autenticar al usuario
    public boolean autenticar(String email, String password) {
        // Buscar el usuario por email y password en el dataStore
        Optional<Usuario> usuarioOpt = dataStore.stream()
                .filter(u -> u.obtenerEmail().equalsIgnoreCase(email) && u.obtenerPassword().equals(password))
                .findFirst();

        if (usuarioOpt.isPresent()) {
        	setUsuarioLogueado(usuarioOpt.get());
            return true; // Autenticación exitosa
        } else {
            return false; // Fallo en la autenticación
        }
    }

    // Obtener el usuario actualmente logueado
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    // Establecer manualmente el usuario logueado (por ejemplo, después de autenticación)
    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
    }

    // Cerrar sesión
    public void cerrarSesion() {
        usuarioLogueado = null;
    }

    // Verificar si hay un usuario logueado
    public boolean isUsuarioLogueado() {
        return usuarioLogueado != null;
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        return dataStore.stream().filter(u -> u.obtenerId() == id).findFirst();
    }

}
