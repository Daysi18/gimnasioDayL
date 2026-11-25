package com.loachamin.gimnasio.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ClaseGrupal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String instructor;
    private String horario;
    private int cupos;
}