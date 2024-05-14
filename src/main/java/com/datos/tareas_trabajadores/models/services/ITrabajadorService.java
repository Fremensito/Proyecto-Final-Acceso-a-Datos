package com.datos.tareas_trabajadores.models.services;

import com.datos.tareas_trabajadores.models.entity.Trabajador;

import java.util.ArrayList;
import java.util.Optional;

public interface ITrabajadorService {

    public void save(Trabajador trabajador);

    public ArrayList<Trabajador> getAll();

    public Optional<Trabajador> delete(String id);

    public Optional<Trabajador> getById(String id);
}
