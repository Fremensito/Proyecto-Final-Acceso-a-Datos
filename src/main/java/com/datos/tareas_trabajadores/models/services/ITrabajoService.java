package com.datos.tareas_trabajadores.models.services;

import com.datos.tareas_trabajadores.models.entity.Trabajo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ITrabajoService {
    //public List<Trabajo> findAll();

    public void save(Trabajo trabajo);

    public ArrayList<Trabajo> getAll();

    public Optional<Trabajo> findById(String id);

    public Optional<Trabajo> delete(String id);

    public Trabajo getLast();

    public List<Trabajo> getTrabajosSinAsignar();

    public List<Trabajo> getTrabajosSinFinalizar();

    public List<Trabajo> getTrabajosFinalizados();
}
