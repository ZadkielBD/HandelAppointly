package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioRespuestaDto;
import com.handel.HandelAppointly.enums.Rol;
import com.handel.HandelAppointly.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @GetMapping("/{id}")
    public String mostrarPorId(@PathVariable Long id, Model modelo) {
        UsuarioRespuestaDto usuario = usuarioServicio.findById(id);
        modelo.addAttribute("usuario", usuario);
        return "usuario";
    }

    @GetMapping
    public String findAll(
            @RequestParam(required = false) Rol rol,
            @PageableDefault(size = 15, sort = "apellido") Pageable pageable,
            Model modelo) {
        Page<UsuarioRespuestaDto> usuarios = usuarioServicio.findAll(rol, pageable);
        modelo.addAttribute("usuarios", usuarios);

        return "usuarios";
    }

    @PutMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioServicio.delete(id);
    }
}
