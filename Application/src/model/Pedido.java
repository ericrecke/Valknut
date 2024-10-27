package model;

import java.math.BigDecimal;
import java.util.Date;

public class Pedido extends EntityModel{
    private Usuario usuario; // FK id_usuario
    private Cliente cliente; // FK id_cliente
    private BigDecimal costoTotal;
    private EnumTipoPago formaPago;
    private Date fechaInicio;
    private Date fechaFin;
    private EnumEstadoPedido estado;

    // Constructor
    public Pedido(int id, Usuario usuario, Cliente cliente, BigDecimal costoTotal, EnumTipoPago formaPago, Date fechaInicio, Date fechaFin, EnumEstadoPedido estado) {
        super(id);
    	this.usuario = usuario;
        this.cliente = cliente;
        this.costoTotal = costoTotal;
        this.formaPago = formaPago;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    // Getters
    public Usuario obtenerUsuario() {
        return usuario;
    }

    public Cliente obtenerCliente() {
        return cliente;
    }

    public Cliente obtenerClienteNombre() {
        return cliente;
    }
    
    public BigDecimal obtenerCostoTotal() {
        return costoTotal;
    }

    public EnumTipoPago obtenerFormaPago() {
        return formaPago;
    }

    public Date obtenerFechaInicio() {
        return fechaInicio;
    }

    public Date obtenerFechaFin() {
        return fechaFin;
    }

    public EnumEstadoPedido obtenerEstado() {
        return estado;
    }

    // Setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setFormaPago(EnumTipoPago formaPago) {
        this.formaPago = formaPago;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstado(EnumEstadoPedido estado) {
        this.estado = estado;
    }


    // Excepción personalizada (opcional)
    public void validarEstado() throws IllegalArgumentException {
        if (!estado.equals("CREADO") && !estado.equals("PENDIENTE") && !estado.equals("PROCESANDO") && !estado.equals("FINALIZADO") && !estado.equals("CANCELADO")) {
            throw new IllegalArgumentException("Estado inválido: " + estado);
        }
    }
}
