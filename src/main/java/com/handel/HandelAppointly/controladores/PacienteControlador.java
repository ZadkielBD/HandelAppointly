package com.handel.HandelAppointly.controladores;

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

    // Crear Cuenta
    @GetMapping("/crear")
    public String crear(Model modelo) {
        modelo.addAttribute("paciente", new PacienteSolicitudDto());
        return "pacientes/crearPaciente";
    }

    @PostMapping("/crear")
    public String crear(@Valid PacienteSolicitudDto pacienteSolicitudDto,
                        BindingResult bindingResult,
                        Model modelo,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("titulo", "Formulario de paciente");
            return "pacientes/crearPaciente";
        }

        pacienteServicio.create(pacienteSolicitudDto);
        redirectAttributes.addFlashAttribute("mensaje", "Paciente guardado correctamente");
        return "redirect:/";
    }

    // Conseguir Paciente
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        pacienteServicio.findById(id);
        return "paciente";
    }

    @GetMapping
    public String findAll(@PageableDefault(size = 10, sort = "lastName")
                                                           Pageable pageable) {
        Page<PacienteRespuestaDto> patient = pacienteServicio.findAll(pageable);
        return "pacientes";
    }

    // Actualizar Paciente
    @GetMapping("/actualizar")
    public String actualizar() {
        return "actualizarPaciente";
    }

    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                             @Valid PacienteSolicitudDto requestDto,
                             RedirectAttributes redirectAttributes) {
        pacienteServicio.update(id, requestDto);
        redirectAttributes.addFlashAttribute("mensaje", "Paciente actualizado");
        return "redirect:/pacientes/" + id;
    }

    @PatchMapping("/actualizar/{id}")
    public String patch(@PathVariable Long id,
                        @Valid PacienteSolicitudDto requestDto,
                        RedirectAttributes redirectAttributes) {
        pacienteServicio.patch(id, requestDto);
        redirectAttributes.addFlashAttribute("mensaje", "Paciente actualizado");
        return "redirect:/pacientes/" + id;
    }

    // Eliminar Paciente
    @PostMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {
        pacienteServicio.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Paciente actualizado");
        return "redirect:/";
    }
}
