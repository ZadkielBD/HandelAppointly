package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.excepciones.EmailDuplicadoException;
import com.handel.HandelAppointly.servicios.DivisaServicio;
import com.handel.HandelAppointly.servicios.DoctorServicio;
import com.handel.HandelAppointly.servicios.EspecialidadServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doctores")
@RequiredArgsConstructor
public class DoctorControlador {
    private final DoctorServicio doctorServicio;
    private final EspecialidadServicio especialidadServicio;
    private final DivisaServicio divisaServicio;

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model modelo) {
        DoctorRespuestaDto doctor = doctorServicio.findById(id);
        modelo.addAttribute("doctor", doctor);
        return "doctor/doctor";
    }

    @GetMapping
    public String findAll(@PageableDefault(size = 15, sort = "apellido") Pageable pageable, Model modelo) {
        Page<DoctorRespuestaDto> doctores = doctorServicio.findAll(pageable);
        modelo.addAttribute("doctores", doctores);
        return "doctor/doctores";
    }

    @GetMapping("/crear")
    public String mostrarCrear(Model modelo) {
        modelo.addAttribute("doctor", new DoctorSolicitudDto());
        modelo.addAttribute("especialidades", especialidadServicio.findAll());
        modelo.addAttribute("divisas", divisaServicio.findAll());
        return "doctor/crearDoctor";
    }

    @PostMapping
    public String procesarCrear(@Valid @ModelAttribute("doctor") DoctorSolicitudDto doctorSolicitudDto,
                        BindingResult result,
                        RedirectAttributes redirectAttributes,
                        Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("especialidades", especialidadServicio.findAll());
            modelo.addAttribute("divisas", divisaServicio.findAll());
            return "doctor/crearDoctor";
        }
        try {
            doctorServicio.create(doctorSolicitudDto);
            redirectAttributes.addFlashAttribute("mensaje", "Doctor guardado exitosamente");
            return "redirect:/";
        } catch (EmailDuplicadoException e) {
            modelo.addAttribute("error", e.getMessage());
            return "paciente/crearPaciente";
        }
    }

    @GetMapping("/actualizar/{id}")
    public String mostrasActualizar(@PathVariable Long id, Model modelo) {
        DoctorRespuestaDto doctor = doctorServicio.findById(id);
        modelo.addAttribute("doctor", doctor);
        modelo.addAttribute("especialidades", especialidadServicio.findAll());
        modelo.addAttribute("divisas", divisaServicio.findAll());
        return "doctor/actualizarDoctor";
    }

    @PostMapping("actualizar/{id}")
    public String procesarActualizar(@PathVariable Long id,
                         @Valid @ModelAttribute("doctor") DoctorSolicitudDto solicitudDto,
                         BindingResult result,
                         Model modelo,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            modelo.addAttribute("especialidades", especialidadServicio.findAll());
            modelo.addAttribute("divisas", divisaServicio.findAll());
            return "doctor/actualizarDoctor";
        }

         doctorServicio.update(id, solicitudDto);
         redirectAttributes.addFlashAttribute("mensaje", "Doctor actualizado");
         return "redirect:/doctores/" + id;
    }

    @PostMapping("eliminar/{id}")
    public String procesarEliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        doctorServicio.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Doctor eliminado");
        return "redirect:/";
    }
}
