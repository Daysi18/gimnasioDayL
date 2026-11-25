package com.loachamin.gimnasio.repository;

import com.loachamin.gimnasio.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaquinaRepository extends JpaRepository<Maquina, Integer> {
    List<Maquina> findByEnMantenimientoFalse();
    List<Maquina> findByAnioCompraLessThan(int anio);
}