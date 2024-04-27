package com.datos.tareas_trabajadores.controllers;


import com.datos.tareas_trabajadores.models.entity.Trabajo;
import com.datos.tareas_trabajadores.models.services.ITrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trabajos")
public class TrabajoController {
    @Autowired
    private ITrabajoService trabajoService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Trabajo create(@RequestBody Trabajo trabajo){
        trabajoService.save(trabajo);
        return trabajo;
    }

    @GetMapping("/last")
    @ResponseStatus(HttpStatus.OK)
    public Trabajo findLast(){
        return trabajoService.getLast();
    }
}
