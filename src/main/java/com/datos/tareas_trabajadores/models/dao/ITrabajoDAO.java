package com.datos.tareas_trabajadores.models.dao;

import com.datos.tareas_trabajadores.models.entity.Trabajo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ITrabajoDAO extends CrudRepository<Trabajo, String> {
    @Query("SELECT t FROM Trabajo t ORDER BY t.codTrabajo DESC LIMIT 1")
    public Trabajo getLast();

    @Query("SELECT t FROM Trabajo t WHERE t.trabajador IS NULL")
    public List<Trabajo> getTrabajosSinAsignar();

    @Query("SELECT t FROM Trabajo t WHERE t.trabajador IS NOT NULL AND t.fecFin IS NULL")
    public List<Trabajo> getTrabajosSinFinalizar();

    @Query("SELECT t FROM Trabajo t WHERE t.trabajador is NOT NULL AND t.fecFin IS NOT NULL")
    public List<Trabajo> getTrabajosFinalizados();
}
