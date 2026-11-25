package com.loachamin.gimnasio.service;

import com.loachamin.gimnasio.model.Socio;
import com.loachamin.gimnasio.repository.SocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SocioService {
    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    public List<Socio> traerTodos() { return socioRepository.findAll(); }
    public Optional<Socio> traerUno(int id) { return socioRepository.findById(id); }
    public Socio guardar(Socio socio) { return socioRepository.save(socio); }

    public Optional<Socio> actualizar(int id, Socio datos) {
        return socioRepository.findById(id).map(s -> {
            s.setNombre(datos.getNombre());
            s.setPlan(datos.getPlan());
            s.setMensualidad(datos.getMensualidad());
            s.setActivo(datos.isActivo());
            return socioRepository.save(s);
        });
    }

    public boolean eliminar(int id) {
        if (!socioRepository.existsById(id)) return false;
        socioRepository.deleteById(id);
        return true;
    }

    public double ingresosActivos() {
        return socioRepository.findAll().stream()
                .filter(Socio::isActivo)
                .mapToDouble(Socio::getMensualidad)
                .sum();
    }

    public Socio desactivar(int id) {
        Socio s = socioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Socio no encontrado"));
        if (!s.isActivo()) throw new IllegalStateException("El socio ya está inactivo");
        s.setActivo(false);
        return socioRepository.save(s);
    }
}