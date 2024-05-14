package com.datos.tareas_trabajadores.models.services;

import com.datos.tareas_trabajadores.models.dao.ITrabajadorDAO;
import com.datos.tareas_trabajadores.models.entity.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements ITrabajadorService {

    @Autowired
    private ITrabajadorDAO trabajadorDAO;

    @Override
    @Transactional
    public void save(Trabajador trabajador){
        trabajadorDAO.save(trabajador);
    }

    @Override
    public ArrayList<Trabajador> getAll(){
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        Iterable<Trabajador> iterable = trabajadorDAO.findAll();
        iterable.forEach(trabajadores::add);
        return trabajadores;
    }

    @Override
    public Optional<Trabajador> delete(String id){
        Optional<Trabajador> t = trabajadorDAO.findById(id);
        if(t.isPresent()){
            Trabajador trabajador = t.get();
            trabajadorDAO.delete(trabajador);
        }
        return t;
    }

    @Override
    public Optional<Trabajador> getById(String id){
        return trabajadorDAO.findById(id);
    }

}
