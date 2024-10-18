package model;

public class Producto {
	//FALTA MODIFICAR
	
    private int id;
    private String nombre;
    private String email;

    // Constructor, getters y setters
    public Producto(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
