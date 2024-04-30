package com.datos.tareas_trabajadores.models.services;

import com.datos.tareas_trabajadores.models.dao.ITrabajoDAO;
import com.datos.tareas_trabajadores.models.entity.Trabajo;
import jakarta.transaction.Transactional;
import jakarta.validation.OverridesAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TrabajoServiceImpl implements ITrabajoService{

    @Autowired
    private ITrabajoDAO trabajoDAO;

    @Override
    @Transactional
    public void save(Trabajo trabajo){
        trabajoDAO.save(trabajo);
    }

    @Override
    public ArrayList<Trabajo> getAll(){
        ArrayList<Trabajo> trabajos = new ArrayList<>();
        Iterable<Trabajo> iterable = trabajoDAO.findAll();
        iterable.forEach(trabajos::add);
        return trabajos;
    }

    @Override
    public Optional<Trabajo> findById(String id){
        return trabajoDAO.findById(id);
    }

    @Override
    @Transactional
    public Optional<Trabajo> delete(String id){
        Optional<Trabajo> t = trabajoDAO.findById(id);
        if(t.isPresent()){
            Trabajo trabajo = t.get();
            trabajoDAO.delete(trabajo);
        }
        return t;
    }

    @Override
    public Trabajo getLast(){
        return trabajoDAO.getLast();
    }
}
