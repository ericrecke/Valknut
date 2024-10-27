package model;

public class Sesion {
    private static Sesion instancia;
    private String mail;

    // Constructor privado para implementar Singleton (solo una sesión a la vez)
    private Sesion() {
    }

    // Método para obtener la instancia de la sesión
    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    // Establecer el mail logueado
    public void iniciarSesion(String mail) {
        this.mail = mail;
    }

    // Cerrar sesión
    public void cerrarSesion() {
        this.mail = null;
    }

    // Verificar si hay un mail logueado
    public boolean isSesionIniciada() {
        return mail != null;
    }

    // Obtener el nombre del mail logueado
    public String getmail() {
        return mail;
    }
}
