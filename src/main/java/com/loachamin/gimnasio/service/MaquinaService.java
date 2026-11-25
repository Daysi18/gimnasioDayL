package com.loachamin.gimnasio.service;

import com.loachamin.gimnasio.model.Maquina;
import com.loachamin.gimnasio.repository.MaquinaRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MaquinaService {
    private final MaquinaRepository maquinaRepository;

    public MaquinaService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    public List<Maquina> traerTodos() { return maquinaRepository.findAll(); }
    public Optional<Maquina> traerUno(int id) { return maquinaRepository.findById(id); }
    public Maquina guardar(Maquina m) { return maquinaRepository.save(m); }

    public Optional<Maquina> actualizar(int id, Maquina datos) {
        return maquinaRepository.findById(id).map(m -> {
            m.setNombre(datos.getNombre());
            m.setMarca(datos.getMarca());
            m.setAnioCompra(datos.getAnioCompra());
            m.setEnMantenimiento(datos.isEnMantenimiento());
            return maquinaRepository.save(m);
        });
    }

    public boolean eliminar(int id) {
        if (!maquinaRepository.existsById(id)) return false;
        maquinaRepository.deleteById(id);
        return true;
    }

    public List<Maquina> operativas() {
        return maquinaRepository.findByEnMantenimientoFalse();
    }

    public List<Maquina> antiguas(int anios) {
        int anioLimite = Year.now().getValue() - anios;
        return maquinaRepository.findByAnioCompraLessThan(anioLimite);
    }

    public Maquina enviarMantenimiento(int id) {
        Maquina m = maquinaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Máquina no encontrada"));
        if (m.isEnMantenimiento()) throw new IllegalStateException("La máquina ya está en mantenimiento");
        m.setEnMantenimiento(true);
        return maquinaRepository.save(m);
    }
}