package com.donaton.necesidades.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "necesidades")
public class Necesidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String ubicacion;
    @Setter
    private String descripcion;
    @Setter
    private String prioridad;
    @Setter
    private String estado;

    public Necesidad() {}


}