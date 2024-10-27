package model;
public enum EnumRol {
    EMPLEADO("Empleado"),
    ADMINISTRADOR("Administrador");

    private final String rol;

    EnumRol(String rol) {
        this.rol = rol;
    }

    public String obtenerRol() {
        return rol;
    }
}