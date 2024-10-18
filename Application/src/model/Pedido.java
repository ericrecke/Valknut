package model;

public class Pedido {
	//FALTA MODIFICAR
	
    private int id;
    private String nombre;
    private String email;

    // Constructor, getters y setters
    public Pedido(int id, String nombre, String email) {
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
