package model;

public class Usuario extends EntityModel {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private EnumSexo sexo;  // EnumSexo en lugar de String
    private EnumRol rol;    // EnumRol en lugar de String

    // Constructor completo
    public Usuario(int id, String nombre, String apellido, String email, String password, EnumSexo sexo, EnumRol rol) {
        super(id);
    	this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.sexo = sexo;
        this.rol = rol;
    }

    // Getters
    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerApellido() {
        return apellido;
    }

    public String obtenerEmail() {
        return email;
    }

    public String obtenerPassword() {
        return password;
    }

    public EnumSexo obtenerSexo() {
        return sexo;
    }

    public EnumRol obtenerRol() {
        return rol;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSexo(EnumSexo sexo) {
        this.sexo = sexo;
    }

    public void setRol(EnumRol rol) {
        this.rol = rol;
    }
}
