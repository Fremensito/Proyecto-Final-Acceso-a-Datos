package com.datos.tareas_trabajadores.models.services;

import com.datos.tareas_trabajadores.models.dao.ITrabajoDAO;
import com.datos.tareas_trabajadores.models.entity.Trabajo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Trabajo getLast(){
        return trabajoDAO.getLast();
    }
}
