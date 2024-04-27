package com.datos.tareas_trabajadores.models.services;

import com.datos.tareas_trabajadores.models.entity.Trabajo;

import java.util.List;

public interface ITrabajoService {
    //public List<Trabajo> findAll();

    public void save(Trabajo trabajo);

    public Trabajo getLast();
}
