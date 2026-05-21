package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.AdministradorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.AdministradorSolicitudDto;
import com.handel.HandelAppointly.enums.NivelAcceso;
import com.handel.HandelAppointly.servicios.AdministradorServicio;
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
@RequestMapping("/administradores")
@RequiredArgsConstructor
public class AdministradorControlador {
    final AdministradorServicio administradorServicio;

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model modelo) {
        AdministradorRespuestaDto respuestaDto = administradorServicio.findById(id);
        modelo.addAttribute("administrador", respuestaDto);
        return "administrador/administrador";
    }

    @GetMapping
    public String findAll(@PageableDefault(size = 15, sort = "apellido") Pageable pageable, Model modelo) {
        Page<AdministradorRespuestaDto> administradores = administradorServicio.findAll(pageable);
        modelo.addAttribute("administradores", administradores);
        return "administrador/administradores";
    }

    @GetMapping("/crear")
    public String crear(Model modelo) {
        modelo.addAttribute("administrador", new AdministradorSolicitudDto());
        modelo.addAttribute("nivelesAcceso", NivelAcceso.values());
        return "administrador/crearAdministrador";
    }

    @PostMapping
    public String crear(@Valid AdministradorSolicitudDto solicitudDto,
                        BindingResult result,
                        RedirectAttributes redirectAttributes,
                        Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("administrador", solicitudDto);
            modelo.addAttribute("nivelesAcceso", NivelAcceso.values());
            return "administrador/crearAdministrador";
        }

        administradorServicio.create(solicitudDto);
        redirectAttributes.addFlashAttribute("mensaje", "Administrador guardado exitosamente");
        return "redirect:/";
    }

    @GetMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, Model modelo) {
        AdministradorRespuestaDto administrador = administradorServicio.findById(id);
        modelo.addAttribute("administrador", administrador);
        return "administrador/actualizarAdministrador";
    }

    @PostMapping("actualizar/{id}")
    public String update(@PathVariable Long id,
                         @Valid AdministradorSolicitudDto solicitudDto,
                         BindingResult result,
                         Model modelo,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            modelo.addAttribute("administrador", solicitudDto);
            return "administrador/actualizarAdministrador";
        }

        administradorServicio.update(id, solicitudDto);
        redirectAttributes.addFlashAttribute("mensaje", "Administrador actualizado");
        return "redirect:/administradores/" + id;
    }

    @PostMapping("eliminar/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        administradorServicio.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Administrador eliminado");
        return "redirect:/";
    }
}
