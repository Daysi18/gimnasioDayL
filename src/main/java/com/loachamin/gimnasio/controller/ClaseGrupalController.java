package com.loachamin.gimnasio.controller;

import com.loachamin.gimnasio.model.ClaseGrupal;
import com.loachamin.gimnasio.service.ClaseGrupalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/clase")
public class ClaseGrupalController {
    private final ClaseGrupalService service;

    public ClaseGrupalController(ClaseGrupalService service) { this.service = service; }

    @GetMapping
    public List<ClaseGrupal> verTodos() { return service.traerTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<ClaseGrupal> traerUno(@PathVariable int id) {
        return service.traerUno(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClaseGrupal> guardar(@RequestBody ClaseGrupal clase) {
        return new ResponseEntity<>(service.guardar(clase), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseGrupal> actualizar(@PathVariable int id, @RequestBody ClaseGrupal clase) {
        return service.actualizar(id, clase).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        return service.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/inscribir")
    public ResponseEntity<?> inscribir(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.inscribir(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar-inscripcion")
    public ResponseEntity<?> cancelar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.cancelar(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/con-cupos")
    public List<ClaseGrupal> conCupos() { return service.conCupos(); }
}