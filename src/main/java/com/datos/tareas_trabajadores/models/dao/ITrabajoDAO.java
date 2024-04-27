package com.datos.tareas_trabajadores.models.dao;

import com.datos.tareas_trabajadores.models.entity.Trabajo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ITrabajoDAO extends CrudRepository<Trabajo, String> {
    @Query("SELECT t FROM Trabajo t ORDER BY t.codTrabajo DESC LIMIT 1")
    public Trabajo getLast();
}
