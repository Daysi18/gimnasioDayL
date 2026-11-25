package com.loachamin.gimnasio.repository;

import com.loachamin.gimnasio.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Integer> {
}