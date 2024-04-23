package com.datos.tareas_trabajadores.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="trabajador")
public class Trabajador implements Serializable {
    @Id
    @Column(length = 5)
    private String id_trabajador;
}
