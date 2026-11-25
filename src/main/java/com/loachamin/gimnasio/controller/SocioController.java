package com.loachamin.gimnasio.controller;

import com.loachamin.gimnasio.model.Socio;
import com.loachamin.gimnasio.service.SocioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/socio")
public class SocioController {
    private final SocioService service;

    public SocioController(SocioService service) { this.service = service; }

    @GetMapping
    public List<Socio> verTodos() { return service.traerTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Socio> traerUno(@PathVariable int id) {
        return service.traerUno(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Socio> guardar(@RequestBody Socio socio) {
        return new ResponseEntity<>(service.guardar(socio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizar(@PathVariable int id, @RequestBody Socio socio) {
        return service.actualizar(id, socio).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        return service.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/ingresos")
    public double ingresos() { return service.ingresosActivos(); }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.desactivar(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}