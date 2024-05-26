package com.datos.tareas_trabajadores.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datos.tareas_trabajadores.models.entity.Trabajo;
import com.datos.tareas_trabajadores.models.services.ITrabajoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/web/trabajos")
public class TrabajoWebController {

    @Autowired
    private ITrabajoService trabajoService;
    
    @GetMapping("/new")
    public String newTrabajo(Model model) {
        model.addAttribute("trabajo", new Trabajo());
        return "formulario_trabajo";
    }

    @PostMapping("/create")
    public String createTrabajo(@Valid Trabajo trabajo, BindingResult result, Model model){
        if(result.hasErrors()){
            return "formulario_trabajo";
        }

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
        return "confirmacion_trabajo";
    }
}
