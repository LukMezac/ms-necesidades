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
    private String prioridad;
    private String estado;
    // Identifica si es "CIUDADANO" o "MUNICIPALIDAD"
    private String origenSolicitud;
    // Identifica la municipalidad específica (ej: "Municipalidad de Viña del Mar")
    private String entidadSolicitante;
}