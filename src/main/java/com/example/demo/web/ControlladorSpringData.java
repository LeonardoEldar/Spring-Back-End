package com.example.demo.web;

import com.example.demo.domain.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ControlladorSpringData {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/springData")
    public String springData(Model model){
        Iterable<Persona> personas = personaService.obtenerPersonas();
        model.addAttribute("personas", personas);
        return "springData";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errors){
        if (errors.hasErrors()) return "modificar";
        personaService.guardar(persona);
        return "redirect:/springData";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        Optional<Persona> persona1 = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona1);
        return "modificar";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/springData";
    }

    @GetMapping("/eliminarV2")
    public String eliminarV2(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/springData";
    }
}
