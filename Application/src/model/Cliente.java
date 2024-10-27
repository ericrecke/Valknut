package model;

public class Cliente extends EntityModel {
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String localidad;


    // Constructor, getters y setters
    public Cliente(int id, String nombre, String apellido, String telefono, String direccion, String localidad) {
    	super(id);
    	this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    //Getters (metodos para obtener)
    
    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerApellido() {
        return apellido;
    }
    
    public String obtenerTelefono() {
        return telefono;
    }
    
    public String obtenerDireccion() {
        return direccion;
    }
    
    public String obtenerLocalidad() {
        return localidad;
    }
    
    // Setters (metodos para setear)
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

}
