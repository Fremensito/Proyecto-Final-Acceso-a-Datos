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
            response.put("result", "error");
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));
            response.put("causa", errores);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
        try {
            trabajoService.save(trabajo);
            response.put("result", "ok");
            response.put("trabajo", trabajo);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        catch(Exception e){
            response.put("result", "error");
            response.put("causa", "internal server error");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Trabajo> getAll(){
        return trabajoService.getAll();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestBody String id){
        Map<String, Object> response = new HashMap<>();
        Optional<Trabajo> trabajo = trabajoService.delete(id);

        if(trabajo.isPresent()){
            response.put("result", "ok");
            response.put("trabajo", trabajo.get());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
        }
        else {
            response.put("result", "error");
            response.put("mensaje", "trabajo no encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/last")
    @ResponseStatus(HttpStatus.OK)
    public Trabajo findLast(){
        return trabajoService.getLast();
    }
}
