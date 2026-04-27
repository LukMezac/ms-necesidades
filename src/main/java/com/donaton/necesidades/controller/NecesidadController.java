package com.donaton.necesidades.controller;

import com.donaton.necesidades.model.Necesidad;
import com.donaton.necesidades.service.NecesidadService;
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
}