package model;
public enum EnumTipoProducto {
    COMIDA("Comida"),
    BEBIDA("Bebida"),
    GUARNICION("Guarnición"),
    POSTRE("Postre");

    private final String tipo;

    EnumTipoProducto(String tipo) {
        this.tipo = tipo;
    }

    public String obtenerTipoProducto() {
        return tipo;
    }
}