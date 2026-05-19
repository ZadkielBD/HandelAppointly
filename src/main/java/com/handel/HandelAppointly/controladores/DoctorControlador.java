package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.servicios.DoctorServicio;
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

    @GetMapping("/crear")
    public String crear(Model modelo) {
        modelo.addAttribute("usuario", new UsuarioRespuestaDto());
        return "doctor/crearDoctor";
    }

    @PostMapping
    public String crear(@Valid DoctorSolicitudDto doctorSolicitudDto,
                        BindingResult result,
                        Model modelo,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "doctor/crearDoctor";
        }

        doctorServicio.create(doctorSolicitudDto);
        redirectAttributes.addFlashAttribute("message", "Doctor guardado exitosamente");
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorRespuestaDto> findById(@PathVariable Long id) {
        DoctorRespuestaDto doctor = doctorServicio.findById(id);
//        return ResponseEntity.ok(doctor);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<DoctorRespuestaDto>> findAll(@PageableDefault(size = 10, sort = "lastName")
                                                           Pageable pageable) {
        Page<DoctorRespuestaDto> doctor = doctorServicio.findAll(pageable);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorRespuestaDto> update(@PathVariable Long id,
                                                     @Valid @RequestBody DoctorSolicitudDto requestDto) {
         DoctorRespuestaDto doctor = doctorServicio.update(id, requestDto);
         return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DoctorRespuestaDto> patch(@PathVariable Long id,
                                                    @Valid @RequestBody DoctorSolicitudDto requestDto) {
        DoctorRespuestaDto doctor = doctorServicio.patch(id, requestDto);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorServicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
