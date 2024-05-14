package com.datos.tareas_trabajadores.models.dao;

import com.datos.tareas_trabajadores.models.entity.Trabajador;
import org.springframework.data.repository.CrudRepository;

public interface ITrabajadorDAO extends CrudRepository<Trabajador,String > {

}
