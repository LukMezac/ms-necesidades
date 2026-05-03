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
        return service.guardar(n);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Necesidad> actualizar(@PathVariable Long id, @RequestBody Necesidad n) {
        Necesidad existente = service.buscarPorId(id);

        if (existente != null) {
            // Esto fallará si no pusiste @Data en la clase Necesidad
            n.setId(id);
            return ResponseEntity.ok(service.guardar(n));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Necesidad existente = service.buscarPorId(id);

        if (existente != null) {
            service.eliminar(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}