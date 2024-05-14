package com.datos.tareas_trabajadores.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="trabajador")
public class Trabajador implements Serializable {
    @Id
    @Column(length = 5)
    private String id_trabajador;

    private String dni;

    private String nombre;

    private String apellidos;

    private String especialidad;

    private String contrasenya;

    private String email;

    @OneToMany(mappedBy = "trabajador")
    private List<Trabajo> trabajos;
}
