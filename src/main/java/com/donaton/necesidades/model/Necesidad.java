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
    private String ubicacion;

    // Constructor vacío (OBLIGATORIO)
    public Necesidad() {}

    // Constructor
    public Necesidad(String recurso, int cantidad, String ubicacion) {
        this.recurso = recurso;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
    public Long getId() { return id; }

    public String getRecurso() { return recurso; }
    public void setRecurso(String recurso) { this.recurso = recurso; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}