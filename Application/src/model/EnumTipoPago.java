package model;
public enum EnumTipoPago {
    EFECTIVO("Efectivo"),
    DEBITO("Débito"),
    CREDITO("Crédito"),
    TRANSFERENCIA("Transferencia");

    private final String tipo;

    EnumTipoPago(String tipo) {
        this.tipo = tipo;
    }

    public String obtenerTipoPago() {
        return tipo;
    }
}