package service;

import model.Usuario;
import service.dao.UsuarioDAO;

import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private Usuario usuarioLogueado;
    private static UsuarioService instancia;
    private final UsuarioDAO usuarioDAO;

    private UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    public static UsuarioService getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioService();
        }
        return instancia;
    }

    // Autenticar al usuario
    public boolean autenticar(String email, String password) {
        // Buscar el usuario por email y contraseña en la base de datos
        List<Usuario> usuarios = usuarioDAO.listar();
        Optional<Usuario> usuarioOpt = usuarios.stream()
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

    // Establecer manualmente el usuario logueado
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

    // Métodos CRUD delegados al DAO
    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    public Optional<Usuario> ver(int id) {
        return usuarioDAO.ver(id);
    }

    public void crear(Usuario usuario) {
        usuarioDAO.crear(usuario);
    }

    public void modificar(Usuario usuario) {
        usuarioDAO.modificar(usuario);
    }

    public void eliminar(int id) {
        usuarioDAO.eliminar(id);
    }
}
