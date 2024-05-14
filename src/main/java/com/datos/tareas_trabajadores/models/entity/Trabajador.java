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


    @Column(length = 9,nullable = false)
    private String dni;

    @Column(length = 100,nullable = false)
    private String nombre;

    @Column(length = 100,nullable = false)
    private String apellidos;
    @Column(length = 50,nullable = false)
    private String especialidad;

    @Column(length = 50,nullable = false)
    private String contraseña;

    @Column(length = 150,unique = true,nullable = false)
    private String email;

    public String getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(String id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
