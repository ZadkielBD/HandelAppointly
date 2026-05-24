package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import com.handel.HandelAppointly.servicios.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteControlador {
    private final PacienteServicio pacienteServicio;

    // Conseguir Paciente
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model modelo) {
        PacienteRespuestaDto paciente = pacienteServicio.findById(id);
        modelo.addAttribute("paciente", paciente);
        return "paciente/paciente";
    }

    @GetMapping
    public String findAll(@PageableDefault(size = 15, sort = "apellido")
                          Pageable pageable,
                          Model modelo) {
        Page<PacienteRespuestaDto> pacientes = pacienteServicio.findAll(pageable);
        modelo.addAttribute("pacientes", pacientes);

        return "paciente/pacientes";
    }


    // Crear Cuenta
    @GetMapping("/crear")
    public String mostrarCrear(Model modelo) {
        modelo.addAttribute("paciente", new PacienteSolicitudDto());
        return "paciente/crearPaciente";
    }

    @PostMapping
    public String procesarCrear(@Valid @ModelAttribute("paciente") PacienteSolicitudDto pacienteSolicitudDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "paciente/crearPaciente";
        }

        pacienteServicio.create(pacienteSolicitudDto);
        redirectAttributes.addFlashAttribute("mensaje", "Paciente guardado correctamente");
        return "redirect:/";
    }

    // Actualizar Paciente
    @GetMapping("/actualizar/{id}")
    public String mostrarActualizar(@PathVariable Long id, Model modelo) {
        PacienteRespuestaDto paciente = pacienteServicio.findById(id);
        modelo.addAttribute("paciente", paciente);
        return "pacielte/actualizarPaciente";
    }

    @PostMapping("/actualizar/{id}")
    public String procesarActualizar(@PathVariable Long id,
                             @Valid @ModelAttribute("paciente") PacienteSolicitudDto requestDto,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "doctor/actualizarDoctor";
        }

        pacienteServicio.update(id, requestDto);
        redirectAttributes.addFlashAttribute("mensaje", "Paciente actualizado");
        return "redirect:/paciente/" + id;
    }

    // Eliminar Paciente
    @PostMapping("/eliminar/{id}")
    public String procesarEliminar(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {
        pacienteServicio.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Paciente eliminado");
        return "redirect:/";
    }
}
