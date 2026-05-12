package com.handel.HandelAppointly.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavegacionControlador {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/crearCuenta")
    public String registrar() {
        return "navegacion/crearCuenta";
    }
}
