package com.datos.tareas_trabajadores.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="trabajo")
public class Trabajo implements Serializable {
    @Id
    //@Pattern(regexp = "t-[1-9](?:[0-9][0-9])?", message = "necesario id tipo t-numero")
    //@Column(nullable = false)
    private String codTrabajo;

    @Size(min=1, max=50, message="longitud de campo no válida")
    @Column(length = 50, nullable = false)
    private String categoria;

    @Column(length = 500, nullable = false)
    private String descripcion;

    @Column(name="fec_ini", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fecIni;

    @Column(name="fec_fin")
    @Temporal(TemporalType.DATE)
    private LocalDate fecFin;

    @Column(columnDefinition = "NUMERIC(4,1)")
    private float tiempo;

    /*@ManyToOne
    @JoinColumn(name="trabajador")
    @Column(name="id_trabajador")
    private Trabajador trabajador;*/

    public String getCodTrabajo() {
        return codTrabajo;
    }

    public void setCodTrabajo(String codTrabajo) {
        this.codTrabajo = codTrabajo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecIni() {
        return fecIni;
    }

    public void setFecIni(LocalDate fecIni) {
        this.fecIni = fecIni;
    }

    public LocalDate getFecFin() {
        return fecFin;
    }

    public void setFecFin(LocalDate fecFin) {
        this.fecFin = fecFin;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    /*public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }*/
}
