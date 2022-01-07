package com.example.demo.ejercicio_1;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Log4j2
public class ControlladorInicio {

    Logger logger = LoggerFactory.getLogger(ControlladorInicio.class);

    @GetMapping("/")
    public ModelAndView inicio(@AuthenticationPrincipal User user){
        logger.info("Ejectunado el controlador Rest");
        logger.info(String.format("User logeado: %S", user.getUsername()));
        //return "Hola Mundo";
        return new ModelAndView("redirect:/springData");
    }
}
