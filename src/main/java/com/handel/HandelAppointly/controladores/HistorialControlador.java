package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioSesionDto;
import com.handel.HandelAppointly.dtos.solicitud.ConsultaMedicaSolicitudDto;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.servicios.HistorialMedicoServicio;
import com.handel.HandelAppointly.servicios.MedicinaServicio;
import com.handel.HandelAppointly.servicios.PacienteServicio;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialControlador {

    private final HistorialMedicoServicio historialMedicoServicio;
    private final PacienteServicio pacienteServicio;
    private final MedicinaServicio medicinaServicio;

    // Doctor: lista de sus pacientes
    @GetMapping("/pacientes")
    public String misPacientes(@PageableDefault(size = 12) Pageable pageable,
                               HttpSession session,
                               Model modelo) {
        UsuarioSesionDto usuario = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";
        if (usuario.getRol() != Rol.DOCTOR) return "redirect:/";

        modelo.addAttribute("pacientes", pacienteServicio.findByDoctorId(usuario.getId(), pageable));
        return "doctor/misPacientes";
    }

    // Doctor: ver historial de un paciente
    @GetMapping("/paciente/{pacienteId}")
    public String verHistorialPaciente(@PathVariable Long pacienteId,
                                       HttpSession session,
                                       Model modelo) {
        UsuarioSesionDto usuario = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";
        if (usuario.getRol() != Rol.DOCTOR) return "redirect:/";

        modelo.addAttribute("historial", historialMedicoServicio.findByPacienteId(pacienteId));
        modelo.addAttribute("paciente", pacienteServicio.findById(pacienteId));
        return "doctor/historialPaciente";
    }

    // Doctor: llenar/editar la consulta de una cita
    @GetMapping("/consulta/{consultaId}")
    public String mostrarLlenarConsulta(@PathVariable Long consultaId,
                                        HttpSession session,
                                        Model modelo) {
        UsuarioSesionDto usuario = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";
        if (usuario.getRol() != Rol.DOCTOR) return "redirect:/";

        modelo.addAttribute("consulta", new ConsultaMedicaSolicitudDto());
        modelo.addAttribute("consultaId", consultaId);
        modelo.addAttribute("medicinas", medicinaServicio.findAll(null));
        return "doctor/llenarConsulta";
    }

    @PostMapping("/consulta/{consultaId}")
    public String procesarLlenarConsulta(@PathVariable Long consultaId,
                                         @Valid @ModelAttribute("consulta") ConsultaMedicaSolicitudDto solicitudDto,
                                         BindingResult result,
                                         HttpSession session,
                                         Model modelo,
                                         RedirectAttributes redirectAttributes) {
        UsuarioSesionDto usuario = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        if (result.hasErrors()) {
            modelo.addAttribute("medicinas", medicinaServicio.findAll(null));
            return "doctor/llenarConsulta";
        }

        historialMedicoServicio.llenarConsulta(consultaId, solicitudDto);
        redirectAttributes.addFlashAttribute("mensaje", "Consulta registrada exitosamente");
        return "redirect:/citas";
    }
}