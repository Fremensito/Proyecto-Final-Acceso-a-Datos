package com.datos.tareas_trabajadores.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datos.tareas_trabajadores.models.entity.Trabajo;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/web/trabajos")
public class TrabajoWebController {
    
    @GetMapping("/new")
    public String newTrabajo(Model model) {
        model.addAttribute("trabajo", new Trabajo());
        return "formulario_trabajo";
    }
}
