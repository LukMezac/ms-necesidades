package com.donaton.necesidades.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "necesidades")
@Data
public class Necesidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ubicacion;
    private String descripcion;
    private Integer cantidad;
    private String prioridad;
    private String estado;
    private String origenSolicitud;
    private String entidadSolicitante;
}