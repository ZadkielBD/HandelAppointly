package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.solicitud.CitaSolicitudDto;
import com.handel.HandelAppointly.enums.TipoConsulta;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.servicios.CitaServicio;
import com.handel.HandelAppointly.servicios.DoctorServicio;
import com.handel.HandelAppointly.servicios.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/citas")
@RequiredArgsConstructor
public class CitaControlador {
    private final CitaServicio citaServicio;
    private final PacienteServicio pacienteServicio;
    private final DoctorServicio doctorServicio;

    @GetMapping
    public String mostrarTodas(@PageableDefault(size = 15, sort = "fechaHora") Pageable pageable,
                               Model modelo) {
        modelo.addAttribute("citas", citaServicio.findAll(pageable));
        return "cita/citas";
    }

    @GetMapping("/{id}")
    public String mostrarPorId(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("cita", citaServicio.findById(id));
        return "cita/cita";
    }

    @GetMapping("/crear")
    public String mostrarCrear(Model modelo) {
        modelo.addAttribute("cita", new CitaSolicitudDto());
        modelo.addAttribute("pacientes", pacienteServicio.findAll(Pageable.unpaged()));
        modelo.addAttribute("doctores", doctorServicio.findAll(Pageable.unpaged()));
        modelo.addAttribute("tiposConsulta", TipoConsulta.values());
        return "cita/crearCita";
    }

    @PostMapping
    public String procesarCrear(@Valid @ModelAttribute("cita") CitaSolicitudDto solicitudDto,
                                BindingResult result,
                                Model modelo,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("pacientes", pacienteServicio.findAll(Pageable.unpaged()));
            modelo.addAttribute("doctores", doctorServicio.findAll(Pageable.unpaged()));
            modelo.addAttribute("tiposConsulta", TipoConsulta.values());
            return "cita/crearCita";
        }

        try {
            citaServicio.create(solicitudDto);
            redirectAttributes.addFlashAttribute("mensaje", "Cita creada exitosamente");
            return "redirect:/citas";
        } catch (ResourcesNotFoundException e) {
            modelo.addAttribute("error", e.getMessage());
            modelo.addAttribute("pacientes", pacienteServicio.findAll(Pageable.unpaged()));
            modelo.addAttribute("doctores", doctorServicio.findAll(Pageable.unpaged()));
            modelo.addAttribute("tiposConsulta", TipoConsulta.values());
            return "cita/crearCita";
        }
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarActualizar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("cita", citaServicio.findById(id));
        modelo.addAttribute("pacientes", pacienteServicio.findAll(Pageable.unpaged()));
        modelo.addAttribute("doctores", doctorServicio.findAll(Pageable.unpaged()));
        modelo.addAttribute("tiposConsulta", TipoConsulta.values());
        return "cita/actualizarCita";
    }

    @PostMapping("/actualizar/{id}")
    public String procesarActualizar(@PathVariable Long id,
                                     @Valid @ModelAttribute("cita") CitaSolicitudDto solicitudDto,
                                     BindingResult result,
                                     Model modelo,
                                     RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            modelo.addAttribute("pacientes", pacienteServicio.findAll(Pageable.unpaged()));
            modelo.addAttribute("doctores", doctorServicio.findAll(Pageable.unpaged()));
            modelo.addAttribute("tiposConsulta", TipoConsulta.values());
            return "cita/actualizarCita";
        }

        citaServicio.update(id, solicitudDto);
        redirectAttributes.addFlashAttribute("mensaje", "Cita actualizada exitosamente");
        return "redirect:/citas/" + id;
    }

    @PostMapping("/eliminar/{id}")
    public String procesarEliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        citaServicio.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Cita eliminada exitosamente");
        return "redirect:/citas";
    }
}
