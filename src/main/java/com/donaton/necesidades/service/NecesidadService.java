package com.donaton.necesidades.service;

import com.donaton.necesidades.model.Necesidad;
import com.donaton.necesidades.repository.NecesidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NecesidadService {

    private final NecesidadRepository repository;

    public NecesidadService(NecesidadRepository repository) {
        this.repository = repository;
    }

    public List<Necesidad> listar() {
        return repository.findAll();
    }

    public Necesidad guardar(Necesidad n) {
        return repository.save(n);
    }

    public Necesidad buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}