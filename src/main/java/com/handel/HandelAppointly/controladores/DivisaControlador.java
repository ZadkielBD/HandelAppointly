package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.servicios.DivisaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/divisas")
@RequiredArgsConstructor
public class DivisaControlador {
    private final DivisaServicio divisaServicio;

    @GetMapping
    public String mostrarTodas(Model modelo) {
        modelo.addAttribute("divisas", divisaServicio.findAll());
        return "divisa/divisas";
    }

    @PostMapping("/actualizar")
    public String actualizarTipoCambio(RedirectAttributes redirectAttributes) {
        divisaServicio.updateTipoCambio();
        redirectAttributes.addFlashAttribute("mensaje", "Tipos de cambio actualizados correctamente");
        return "redirect:/divisas";
    }
}