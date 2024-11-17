package model;

import java.math.BigDecimal;

public class Producto extends EntityModel {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private EnumTipoProducto tipo;

    // Constructor
    public Producto(int id, String nombre, String descripcion, BigDecimal precio, EnumTipoProducto tipo) {
    	super(id);
    	this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int obtenerId() {
        return id;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public BigDecimal obtenerPrecio() {
        return precio;
    }

    public EnumTipoProducto obtenerTipo() {
        return tipo;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public void setTipo(EnumTipoProducto tipo) {
        this.tipo = tipo;
    }

}
