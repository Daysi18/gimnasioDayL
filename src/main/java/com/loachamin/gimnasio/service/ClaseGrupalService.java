package com.loachamin.gimnasio.service;

import com.loachamin.gimnasio.model.ClaseGrupal;
import com.loachamin.gimnasio.repository.ClaseGrupalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClaseGrupalService {
    private final ClaseGrupalRepository claseRepository;

    public ClaseGrupalService(ClaseGrupalRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public List<ClaseGrupal> traerTodos() { return claseRepository.findAll(); }
    public Optional<ClaseGrupal> traerUno(int id) { return claseRepository.findById(id); }
    public ClaseGrupal guardar(ClaseGrupal c) { return claseRepository.save(c); }

    public Optional<ClaseGrupal> actualizar(int id, ClaseGrupal datos) {
        return claseRepository.findById(id).map(c -> {
            c.setNombre(datos.getNombre());
            c.setInstructor(datos.getInstructor());
            c.setHorario(datos.getHorario());
            c.setCupos(datos.getCupos());
            return claseRepository.save(c);
        });
    }

    public boolean eliminar(int id) {
        if (!claseRepository.existsById(id)) return false;
        claseRepository.deleteById(id);
        return true;
    }

    public ClaseGrupal inscribir(int id) {
        ClaseGrupal c = claseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Clase no encontrada"));
        if (c.getCupos() <= 0) throw new IllegalStateException("No quedan cupos disponibles");
        c.setCupos(c.getCupos() - 1);
        return claseRepository.save(c);
    }

    public ClaseGrupal cancelar(int id) {
        ClaseGrupal c = claseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Clase no encontrada"));
        c.setCupos(c.getCupos() + 1);
        return claseRepository.save(c);
    }

    public List<ClaseGrupal> conCupos() {
        return claseRepository.findByCuposGreaterThanOrderByCuposDesc(0);
    }
}