package com.donaton.necesidades.model;

import jakarta.persistence.*;
import lombok.Data; // Asegúrate de tener esta importación

@Entity
@Table(name = "necesidades")
@Data
public class Necesidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ubicacion;
    private String descripcion;
    private String prioridad;
    private String estado;
}