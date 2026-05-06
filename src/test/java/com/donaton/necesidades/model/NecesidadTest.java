package com.donaton.necesidades.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NecesidadTest {

    @Test
    void aceptaEntradasValidas() {
        Necesidad necesidad = new Necesidad();
        necesidad.setId(1L);
        necesidad.setUbicacion("Bogota");
        necesidad.setDescripcion("Alimentos");
        necesidad.setPrioridad("ALTA");
        necesidad.setEstado("PENDIENTE");

        assertEquals(1L, necesidad.getId());
        assertEquals("Bogota", necesidad.getUbicacion());
        assertEquals("Alimentos", necesidad.getDescripcion());
        assertEquals("ALTA", necesidad.getPrioridad());
        assertEquals("PENDIENTE", necesidad.getEstado());
    }

    @Test
    void aceptaEntradasInvalidasSinValidacion() {
        Necesidad necesidad = new Necesidad();
        necesidad.setUbicacion("");
        necesidad.setDescripcion("###");
        necesidad.setPrioridad("?");
        necesidad.setEstado("DESCONOCIDO");

        assertEquals("", necesidad.getUbicacion());
        assertEquals("###", necesidad.getDescripcion());
        assertEquals("?", necesidad.getPrioridad());
        assertEquals("DESCONOCIDO", necesidad.getEstado());
    }

    @Test
    void aceptaNullEnCampos() {
        Necesidad necesidad = new Necesidad();
        necesidad.setUbicacion(null);
        necesidad.setDescripcion(null);
        necesidad.setPrioridad(null);
        necesidad.setEstado(null);

        assertNull(necesidad.getUbicacion());
        assertNull(necesidad.getDescripcion());
        assertNull(necesidad.getPrioridad());
        assertNull(necesidad.getEstado());
    }
}
