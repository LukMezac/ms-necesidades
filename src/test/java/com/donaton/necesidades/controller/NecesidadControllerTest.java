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
        Necesidad n = necesidad(1L, "Bogota", "Agua", "ALTA", "PENDIENTE");
        when(necesidadService.listar()).thenReturn(List.of(n));

        mockMvc.perform(get("/necesidades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descripcion").value("Agua"));
    }

    @Test
    void obtenerPorIdRetorna200() throws Exception {
        when(necesidadService.buscarPorId(1L)).thenReturn(necesidad(1L, "Bogota", "Alimentos", "MEDIA", "PENDIENTE"));

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
                .thenReturn(necesidad(2L, "Cali", "Mantas", "BAJA", "PENDIENTE"));

        mockMvc.perform(post("/necesidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "ubicacion":"Cali",
                                  "descripcion":"Mantas",
                                  "prioridad":"BAJA",
                                  "estado":"PENDIENTE"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.descripcion").value("Mantas"));
    }

    @Test
    void actualizarRetorna200() throws Exception {
        when(necesidadService.buscarPorId(3L)).thenReturn(necesidad(3L, "Medellin", "Comida", "ALTA", "PENDIENTE"));
        when(necesidadService.guardar(any(Necesidad.class)))
                .thenReturn(necesidad(3L, "Medellin", "Comida actualizada", "ALTA", "ATENDIDA"));

        mockMvc.perform(put("/necesidades/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "ubicacion":"Medellin",
                                  "descripcion":"Comida actualizada",
                                  "prioridad":"ALTA",
                                  "estado":"ATENDIDA"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.estado").value("ATENDIDA"));
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
                                  "prioridad":"ALTA",
                                  "estado":"PENDIENTE"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarRetorna200() throws Exception {
        when(necesidadService.buscarPorId(5L)).thenReturn(necesidad(5L, "Barranquilla", "Agua", "MEDIA", "PENDIENTE"));
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

    private Necesidad necesidad(Long id, String ubicacion, String descripcion, String prioridad, String estado) {
        Necesidad necesidad = new Necesidad();
        necesidad.setId(id);
        necesidad.setUbicacion(ubicacion);
        necesidad.setDescripcion(descripcion);
        necesidad.setPrioridad(prioridad);
        necesidad.setEstado(estado);
        return necesidad;
    }
}
