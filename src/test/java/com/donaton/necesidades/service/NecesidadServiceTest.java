package com.donaton.necesidades.service;

import com.donaton.necesidades.model.Necesidad;
import com.donaton.necesidades.repository.NecesidadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NecesidadServiceTest {

    @Mock
    private NecesidadRepository necesidadRepository;

    @InjectMocks
    private NecesidadService necesidadService;

    @Test
    void listarRetornaElementos() {
        when(necesidadRepository.findAll()).thenReturn(List.of(necesidad(1L), necesidad(2L)));

        List<Necesidad> resultado = necesidadService.listar();

        assertEquals(2, resultado.size());
    }

    @Test
    void guardarHappyPath() {
        Necesidad entrada = necesidad(null);
        when(necesidadRepository.save(entrada)).thenReturn(necesidad(10L));

        Necesidad guardada = necesidadService.guardar(entrada);

        assertEquals(10L, guardada.getId());
        verify(necesidadRepository).save(entrada);
    }

    @Test
    void guardarPropagaError() {
        Necesidad entrada = necesidad(null);
        when(necesidadRepository.save(entrada)).thenThrow(new RuntimeException("Error guardando"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> necesidadService.guardar(entrada));

        assertEquals("Error guardando", ex.getMessage());
    }

    @Test
    void buscarPorIdRetornaEntidad() {
        when(necesidadRepository.findById(1L)).thenReturn(Optional.of(necesidad(1L)));

        Necesidad resultado = necesidadService.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void buscarPorIdRetornaNullCuandoNoExiste() {
        when(necesidadRepository.findById(2L)).thenReturn(Optional.empty());

        Necesidad resultado = necesidadService.buscarPorId(2L);

        assertNull(resultado);
    }

    @Test
    void eliminarHappyPath() {
        necesidadService.eliminar(3L);

        verify(necesidadRepository).deleteById(3L);
    }

    @Test
    void eliminarPropagaError() {
        doThrow(new RuntimeException("Error eliminando")).when(necesidadRepository).deleteById(4L);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> necesidadService.eliminar(4L));

        assertEquals("Error eliminando", ex.getMessage());
    }

    private Necesidad necesidad(Long id) {
        Necesidad necesidad = new Necesidad();
        necesidad.setId(id);
        necesidad.setDescripcion("Descripcion");
        necesidad.setUbicacion("Ubicacion");
        necesidad.setPrioridad("ALTA");
        necesidad.setEstado("PENDIENTE");
        return necesidad;
    }
}
