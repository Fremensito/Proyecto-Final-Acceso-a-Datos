package com.datos.tareas_trabajadores.controllers;

import com.datos.tareas_trabajadores.models.entity.Trabajador;
import com.datos.tareas_trabajadores.models.services.ITrabajadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/trabajadores")
public class TrabajadorController {

    @Autowired
    private ITrabajadorService trabajadorService;

    // Crear un nuevo trabajador
    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Trabajador trabajador, BindingResult result){
        if(result.hasErrors()){
            // Manejo de errores de validación
            return new ResponseEntity<>(manejarErrores(result), HttpStatus.CONFLICT);
        }
        try {
            trabajadorService.save(trabajador);
            return new ResponseEntity<>(respuestaExitosa(trabajador), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(respuestaError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los trabajadores
    @GetMapping("")
    public ArrayList<Trabajador> getAll(){
        return trabajadorService.getAll();
    }

    // Eliminar un trabajador por ID
    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestBody String id){
        return handleTrabajadorResponse(trabajadorService.delete(id));
    }

    // Obtener un trabajador por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTrabajador(@PathVariable String id){
        return handleTrabajadorResponse(trabajadorService.getById(id));
    }

    // Método privado para manejar la respuesta de un trabajador
    private ResponseEntity<?> handleTrabajadorResponse(Optional<Trabajador> trabajador) {
        if (trabajador.isPresent()) {
            return new ResponseEntity<>(respuestaExitosa(trabajador.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuestaError("trabajador no encontrado"), HttpStatus.NOT_FOUND);
        }
    }

    // Métodos privados para manejar las respuestas y errores
    private Map<String, Object> respuestaExitosa(Trabajador trabajador) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", "ok");
        response.put("trabajador", trabajador);
        return response;
    }

    private Map<String, Object> respuestaError(String mensaje) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", "error");
        response.put("mensaje", mensaje);
        return response;
    }

    private Map<String, Object> manejarErrores(BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", "error");
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));
        response.put("causa", errores);
        return response;
    }
}
