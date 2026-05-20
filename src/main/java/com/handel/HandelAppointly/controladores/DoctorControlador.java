package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.repositorios.EspecialidadRepositorio;
import com.handel.HandelAppointly.servicios.DoctorServicio;
import com.handel.HandelAppointly.servicios.EspecialidadServicio;
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
@RequestMapping("/doctores")
@RequiredArgsConstructor
public class DoctorControlador {
    private final DoctorServicio doctorServicio;
    private final EspecialidadServicio especialidadServicio;

    @GetMapping("/crear")
    public String crear(Model modelo) {
        modelo.addAttribute("doctor", new DoctorSolicitudDto());
        modelo.addAttribute("especialidades", especialidadServicio.findAll());
        return "doctor/crearDoctor";
    }

    @PostMapping
    public String crear(@Valid DoctorSolicitudDto doctorSolicitudDto,
                        BindingResult result,
                        RedirectAttributes redirectAttributes,
                        Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("doctor", doctorSolicitudDto);
            modelo.addAttribute("especialidades", especialidadServicio.findAll());
            return "doctor/crearDoctor";
        }

        doctorServicio.create(doctorSolicitudDto);
        redirectAttributes.addFlashAttribute("mensaje", "Doctor guardado exitosamente");
        return "redirect:/";
    }

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

    @GetMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, Model modelo) {
        DoctorRespuestaDto doctor = doctorServicio.findById(id);
        modelo.addAttribute("doctor", doctor);
        return "doctor/actualizarDoctor";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid DoctorSolicitudDto solicitudDto,
                         BindingResult result,
                         Model modelo,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            modelo.addAttribute("doctor", solicitudDto);
            return "doctor/actualizarDoctor";
        }

         doctorServicio.update(id, solicitudDto);
         redirectAttributes.addFlashAttribute("mensaje", "Doctor actualizado");
         return "redirect:/doctores/" + id;
    }

    @PatchMapping("/{id}")
    public String patch(@PathVariable Long id,
                        @Valid DoctorSolicitudDto solicitudDto,
                        BindingResult result,
                        Model modelo,
                        RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            modelo.addAttribute("doctor", solicitudDto);
            return "doctor/actualizarDoctor";
        }

        doctorServicio.patch(id, solicitudDto);
        redirectAttributes.addFlashAttribute("mensaje", "Doctor actualizado");
        return "redirect:/doctores/" + id;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorServicio.delete(id);
    }
}
