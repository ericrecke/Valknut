package model;

public class PedidoProducto {
    private Pedido pedido;
    private Producto producto;
    private int cantidad;

    public PedidoProducto(Pedido pedido, Producto producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Pedido obtenerPedido() {
        return pedido;
    }

    public Producto obtenerProducto() {
        return producto;
    }

    public int obtenerCantidad() {
        return cantidad;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
