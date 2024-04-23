package com.datos.tareas_trabajadores.models.dao;

import com.datos.tareas_trabajadores.models.entity.Trabajo;
import org.springframework.data.repository.CrudRepository;

public interface ITrabajoDAO extends CrudRepository<Trabajo, String> {

}
