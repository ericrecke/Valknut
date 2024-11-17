package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido extends EntityModel {
    private Usuario usuario;
    private Cliente cliente;
    private BigDecimal costoTotal;
    private EnumTipoPago formaPago;
    private Date fechaInicio;
    private Date fechaFin;
    private EnumEstadoPedido estado;
    private List<PedidoProducto> productos= new ArrayList<>(); // Relación con PedidoProducto

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

    // Getters y setters
    public Usuario obtenerUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente obtenerCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal obtenerCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public EnumTipoPago obtenerFormaPago() {
        return formaPago;
    }

    public void setFormaPago(EnumTipoPago formaPago) {
        this.formaPago = formaPago;
    }

    public Date obtenerFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date obtenerFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EnumEstadoPedido obtenerEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoPedido estado) {
        this.estado = estado;
    }

    public List<PedidoProducto> obtenerProductos() {
        return productos;
    }

    public void setProductos(List<PedidoProducto> productos) {
        this.productos = productos;
    }
}
