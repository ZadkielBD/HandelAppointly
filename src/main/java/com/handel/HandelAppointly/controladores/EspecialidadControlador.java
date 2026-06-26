package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.EspecialidadRespuestaDto;
import com.handel.HandelAppointly.servicios.EspecialidadServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/especialidades")
@RequiredArgsConstructor
public class EspecialidadControlador {
    private final EspecialidadServicio especialidadServicio;

    @GetMapping
    public String findAll(Model modelo) {
        modelo.addAttribute("especialidades", especialidadServicio.findAll());
        modelo.addAttribute("nueva", new EspecialidadRespuestaDto(null, "", ""));
        return "especialidad/especialidades";
    }

    @PostMapping
    public String crear(@RequestParam String nombre,
                        @RequestParam(required = false) String descripcion,
                        RedirectAttributes redirectAttributes) {
        especialidadServicio.create(nombre, descripcion);
        redirectAttributes.addFlashAttribute("mensaje", "Especialidad creada");
        return "redirect:/especialidades";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                             @RequestParam String nombre,
                             @RequestParam(required = false) String descripcion,
                             RedirectAttributes redirectAttributes) {
        especialidadServicio.update(id, nombre, descripcion);
        redirectAttributes.addFlashAttribute("mensaje", "Especialidad actualizada");
        return "redirect:/especialidades";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        especialidadServicio.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Especialidad eliminada");
        return "redirect:/especialidades";
    }
}