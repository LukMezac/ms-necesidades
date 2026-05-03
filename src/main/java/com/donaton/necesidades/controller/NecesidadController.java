package com.donaton.necesidades.controller;

import com.donaton.necesidades.model.Necesidad;
import com.donaton.necesidades.service.NecesidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/necesidades")
@CrossOrigin("*")
public class NecesidadController {

    private final NecesidadService service;

    public NecesidadController(NecesidadService service) {
        this.service = service;
    }


    @GetMapping
    public List<Necesidad> listar() {
        return service.listar();
    }

    @PostMapping
    public Necesidad crear(@RequestBody Necesidad n) {
        n.setEstado("Pendiente"); // 🔥 estado controlado por backend
        return service.guardar(n);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Necesidad n) {

        Necesidad actual = service.buscarPorId(id);

        if (actual == null) {
            return ResponseEntity.notFound().build();
        }

        actual.setUbicacion(n.getUbicacion());
        actual.setDescripcion(n.getDescripcion());
        actual.setPrioridad(n.getPrioridad());
        actual.setEstado(n.getEstado());
        return ResponseEntity.ok(service.guardar(actual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Eliminado correctamente");
    }

}