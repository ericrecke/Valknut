package model;
public enum EnumEstadoPedido {
    CREADO("Creado"),
    PENDIENTE("Pendiente"),
    PROCESANDO("Procesando"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String estado;

    EnumEstadoPedido(String estado) {
        this.estado = estado;
    }

    public String obtenerEstado() {
        return estado;
    }
}