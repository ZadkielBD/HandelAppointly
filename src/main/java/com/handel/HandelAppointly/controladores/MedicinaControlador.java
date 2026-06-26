package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.MedicinaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.MedicinaSolicitudDto;
import com.handel.HandelAppointly.entidades.Medicina;
import com.handel.HandelAppointly.servicios.MedicinaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/medicinas")
@RequiredArgsConstructor
public class MedicinaControlador {
    private final MedicinaServicio medicinaServicio;

    @GetMapping
    public String mostrarTodas(Model modelo,
                               @PageableDefault(size = 12, sort = "apellido") Pageable pageable) {
        modelo.addAttribute("medicinas", medicinaServicio.findAll(pageable));
        modelo.addAttribute("nueva", new Medicina());
        return "medicina/medicinas";
    }

    @GetMapping("/crear")
    public String mostrarCrear(Model modelo) {
        modelo.addAttribute("medicina", new MedicinaSolicitudDto());
        return "medicina/crearMedicina";
    }

    @PostMapping
    public String procesarCrear(@Valid @ModelAttribute("medicina") MedicinaSolicitudDto medicina,
                        RedirectAttributes redirectAttributes) {
        medicinaServicio.create(medicina);
        redirectAttributes.addFlashAttribute("mensaje", "Medicina creada exitosamente");
        return "redirect:/medicinas";
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarActualizar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("medicina", medicinaServicio.findById(id));
        return "medicina/actualizarMedicina";
    }

    @PostMapping("/actualizar/{id}")
    public String procesarActualizar(@PathVariable Long id,
                             @Valid @ModelAttribute("medicina") MedicinaSolicitudDto medicina,
                             RedirectAttributes redirectAttributes) {
        medicinaServicio.update(id, medicina);
        redirectAttributes.addFlashAttribute("mensaje", "Medicina actualizada");
        return "redirect:/medicinas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        medicinaServicio.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Medicina eliminada");
        return "redirect:/medicinas";
    }
}
