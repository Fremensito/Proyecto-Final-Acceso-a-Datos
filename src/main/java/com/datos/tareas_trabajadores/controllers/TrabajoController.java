package com.datos.tareas_trabajadores.controllers;


import com.datos.tareas_trabajadores.models.entity.Trabajo;
import com.datos.tareas_trabajadores.models.services.ITrabajoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/trabajos")
public class TrabajoController {
    @Autowired
    private ITrabajoService trabajoService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Trabajo trabajo, BindingResult result){
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            return validate(response, result);
        }

        //Registro del trabajo
        try {
            Trabajo ultimoTrabajo = trabajoService.getLast();
            if(ultimoTrabajo == null)
                trabajo.setCodTrabajo("t-1");
            else{
                String[] cod_spliteado = ultimoTrabajo.getCodTrabajo().split("-");
                trabajo.setCodTrabajo(cod_spliteado[0] +
                        "-" +
                        String.valueOf(Integer.parseInt(cod_spliteado[1])+ 1));
            }
            trabajoService.save(trabajo);
            response.put("result", "ok");
            response.put("trabajo", trabajo);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        catch(Exception e){
            response.put("result", "error");
            response.put("causa", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Trabajo> getAll(){
        return trabajoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Map<String, Object> response = new HashMap<>();
        Optional<Trabajo> trabajo = trabajoService.findById(id);

        return getOrDelete(response, trabajo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable String id, @Valid @RequestBody Trabajo trabajo, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors())
            return validate(response, result);
        else{
            Optional<Trabajo> db_trabajo = trabajoService.findById(id);
            if(db_trabajo.isPresent()){
                trabajo.setCodTrabajo(db_trabajo.get().getCodTrabajo());
                trabajoService.save(trabajo);
                response.put("result", "ok");
                response.put("trabajo", trabajo);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            else{
                response.put("result", "error");
                response.put("causa", "trabajo no encontrado");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Map<String, Object> response = new HashMap<>();
        Optional<Trabajo> trabajo = trabajoService.delete(id);

        return getOrDelete(response, trabajo);
    }

    @GetMapping("/last")
    @ResponseStatus(HttpStatus.OK)
    public Trabajo findLast(){
        return trabajoService.getLast();
    }

    private ResponseEntity<Map<String, Object>> validate(Map<String, Object> response, BindingResult result){
        response.put("result", "error");
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));
        response.put("causa", errores);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Map<String, Object>> getOrDelete(Map<String, Object> response, Optional<Trabajo> trabajo){
        if(trabajo.isPresent()){
            response.put("result", "ok");
            response.put("trabajo", trabajo.get());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
        }
        else {
            response.put("result", "error");
            response.put("causa", "trabajo no encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }
}
