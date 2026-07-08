package com.donaton.necesidades.controller;

import com.donaton.necesidades.model.Necesidad;
import com.donaton.necesidades.service.NecesidadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = com.donaton.necesidades.MsNecesidadesApplication.class)
@WebMvcTest(NecesidadController.class)
class NecesidadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NecesidadService necesidadService;

    @Test
    void listarRetorna200() throws Exception {
        Necesidad n = necesidad(1L, "Bogota", "Agua", 100, "ALTA", "PENDIENTE", "CIUDADANO", "N/A");
        when(necesidadService.listar()).thenReturn(List.of(n));

        mockMvc.perform(get("/necesidades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descripcion").value("Agua"))
                .andExpect(jsonPath("$[0].cantidad").value(100)); // Validamos cantidad
    }

    @Test
    void obtenerPorIdRetorna200() throws Exception {
        when(necesidadService.buscarPorId(1L)).thenReturn(necesidad(1L, "Bogota", "Alimentos", 50, "MEDIA", "PENDIENTE", "MUNICIPALIDAD", "Muni Central"));

        mockMvc.perform(get("/necesidades/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.ubicacion").value("Bogota"));
    }

    @Test
    void obtenerPorIdRetorna404() throws Exception {
        when(necesidadService.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/necesidades/{id}", 99L))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearRetorna200() throws Exception {
        when(necesidadService.guardar(any(Necesidad.class)))
                .thenReturn(necesidad(2L, "Cali", "Mantas", 20, "BAJA", "PENDIENTE", "CIUDADANO", "N/A"));

        mockMvc.perform(post("/necesidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "ubicacion":"Cali",
                                  "descripcion":"Mantas",
                                  "cantidad": 20,
                                  "prioridad":"BAJA",
                                  "estado":"PENDIENTE",
                                  "origenSolicitud":"CIUDADANO",
                                  "entidadSolicitante":"N/A"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.descripcion").value("Mantas"))
                .andExpect(jsonPath("$.cantidad").value(20)); // Validamos que guarde la cantidad
    }

    @Test
    void actualizarRetorna200() throws Exception {
        when(necesidadService.buscarPorId(3L)).thenReturn(necesidad(3L, "Medellin", "Comida", 30, "ALTA", "PENDIENTE", "MUNICIPALIDAD", "Muni Norte"));
        when(necesidadService.guardar(any(Necesidad.class)))
                .thenReturn(necesidad(3L, "Medellin", "Comida actualizada", 50, "ALTA", "ATENDIDA", "MUNICIPALIDAD", "Muni Norte"));

        mockMvc.perform(put("/necesidades/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "ubicacion":"Medellin",
                                  "descripcion":"Comida actualizada",
                                  "cantidad": 50,
                                  "prioridad":"ALTA",
                                  "estado":"ATENDIDA",
                                  "origenSolicitud":"MUNICIPALIDAD",
                                  "entidadSolicitante":"Muni Norte"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.estado").value("ATENDIDA"))
                .andExpect(jsonPath("$.cantidad").value(50));
    }

    @Test
    void actualizarRetorna404() throws Exception {
        when(necesidadService.buscarPorId(404L)).thenReturn(null);

        mockMvc.perform(put("/necesidades/{id}", 404L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "ubicacion":"Medellin",
                                  "descripcion":"Comida",
                                  "cantidad": 30,
                                  "prioridad":"ALTA",
                                  "estado":"PENDIENTE",
                                  "origenSolicitud":"MUNICIPALIDAD",
                                  "entidadSolicitante":"Muni Norte"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarRetorna200() throws Exception {
        when(necesidadService.buscarPorId(5L)).thenReturn(necesidad(5L, "Barranquilla", "Agua", 10, "MEDIA", "PENDIENTE", "CIUDADANO", "N/A"));
        doNothing().when(necesidadService).eliminar(eq(5L));

        mockMvc.perform(delete("/necesidades/{id}", 5L))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarRetorna404() throws Exception {
        when(necesidadService.buscarPorId(500L)).thenReturn(null);

        mockMvc.perform(delete("/necesidades/{id}", 500L))
                .andExpect(status().isNotFound());
    }

    private Necesidad necesidad(Long id, String ubicacion, String descripcion, Integer cantidad, String prioridad, String estado, String origenSolicitud, String entidadSolicitante) {
        Necesidad necesidad = new Necesidad();
        necesidad.setId(id);
        necesidad.setUbicacion(ubicacion);
        necesidad.setDescripcion(descripcion);
        necesidad.setCantidad(cantidad); // <-- Agregado
        necesidad.setPrioridad(prioridad);
        necesidad.setEstado(estado);
        necesidad.setOrigenSolicitud(origenSolicitud); // <-- Agregado
        necesidad.setEntidadSolicitante(entidadSolicitante); // <-- Agregado
        return necesidad;
    }
}