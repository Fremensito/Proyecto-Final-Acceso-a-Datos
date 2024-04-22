package com.datos.tareas_trabajadores.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController{

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
    }

    @GetMapping({"/index", "/", "", "/home"})
    public String index(Model model){
        model.addAttribute("mensajito", "dice: ");
        return "index";
    }
}
