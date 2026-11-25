package com.loachamin.gimnasio.controller;

import com.loachamin.gimnasio.model.Maquina;
import com.loachamin.gimnasio.service.MaquinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/maquina")
public class MaquinaController {
    private final MaquinaService service;

    public MaquinaController(MaquinaService service) { this.service = service; }

    @GetMapping
    public List<Maquina> verTodos() { return service.traerTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> traerUno(@PathVariable int id) {
        return service.traerUno(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Maquina> guardar(@RequestBody Maquina maquina) {
        return new ResponseEntity<>(service.guardar(maquina), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maquina> actualizar(@PathVariable int id, @RequestBody Maquina maquina) {
        return service.actualizar(id, maquina).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        return service.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/operativas")
    public List<Maquina> operativas() { return service.operativas(); }

    @GetMapping("/antiguas")
    public List<Maquina> antiguas(@RequestParam(defaultValue = "5") int anios) {
        return service.antiguas(anios);
    }

    @PutMapping("/{id}/mantenimiento")
    public ResponseEntity<?> mantenimiento(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.enviarMantenimiento(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}