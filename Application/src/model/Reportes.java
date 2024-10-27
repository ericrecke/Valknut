package model;

import java.util.Date;

public class Reportes extends EntityModel {
    private Usuario usuario;
    private Date fechaInicio;
    private Date fechaFin;
    
    // Constructor completo
    public Reportes(int id, Usuario usuario, Date fechaInicio, Date fechaFin) {
        super(id);
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters
    public Usuario obtenerUsuario() {
        return usuario;
    }

    public Date obtenerFechaInicio() {
        return fechaInicio;
    }

    public Date obtenerFechaFin() {
        return fechaFin;
    }

    // Setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
