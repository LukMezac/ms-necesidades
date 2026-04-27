package com.donaton.necesidades.model;

import jakarta.persistence.*;

@Entity
@Table(name = "necesidades")
public class Necesidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recurso;
    private int cantidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Necesidad(Long id, String recurso, int cantidad, String ubicacion, String prioridad) {
        this.id = id;
        this.recurso = recurso;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.prioridad = prioridad;
    }

    private String ubicacion;
    private String prioridad;

    // Constructor vacío (OBLIGATORIO)
    public Necesidad() {}

}