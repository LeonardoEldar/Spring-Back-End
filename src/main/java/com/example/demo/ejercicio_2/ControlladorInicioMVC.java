package com.example.demo.ejercicio_2;

import com.example.demo.domain.Persona;
import com.example.demo.domain.PersonaTest;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
public class ControlladorInicioMVC {

    Logger logger = LoggerFactory.getLogger(ControlladorInicioMVC.class);

    //Cargo valor de application.properties
    @Value("${index.saludo}") private String saludo;

    @GetMapping("/mvc")
    public String inicio(Model model){
        logger.info("Ejectunado el controlador MVC");
        String mensaje = "Hola Mundo Thymeleaf";

        PersonaTest persona_01 = PersonaTest.builder()
                .nombre("Leo")
                .apellido("Di Primo")
                .email("leo@gmail.com")
                .telefono("1313213")
                .build();

        PersonaTest persona_02 = PersonaTest.builder()
                .nombre("Agus")
                .apellido("Di Primo")
                .email("agus@gmail.com")
                .telefono("45646456")
                .build();

        List<PersonaTest> personas = Arrays.asList(persona_01, persona_02);

        model.addAttribute("mensaje", mensaje);
        model.addAttribute("saludo", saludo);
        model.addAttribute("personas", personas);

        return "index";
    }
}
