package model;

public class EntityModel {
	protected int id;
    
    // Constructor sin parámetros
    public EntityModel() {}
	
    // Constructor con parámetro
    public EntityModel(int id) {
    	this.id = id;
    }
    
    // Metodo get para Id modelo base.
    public int obtenerId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
}
