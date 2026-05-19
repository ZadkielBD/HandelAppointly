package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.UsuarioSesionDto;
import com.handel.HandelAppointly.dtos.solicitud.LoginSolicitudDto;
import com.handel.HandelAppointly.entidades.Usuario;
import com.handel.HandelAppointly.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class NavegacionControlador {

    private final UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/crearCuenta")
    public String mostrarCrearCuenta() {
        return "navegacion/crearCuenta";
    }

    // Muestra el formulario de login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("loginDto", new LoginSolicitudDto());
        return "navegacion/login";
    }

    // Procesa el formulario de login
    @PostMapping("/login")
    public String procesarLogin(@Valid LoginSolicitudDto loginDto,
                                BindingResult bindingResult,
                                HttpSession session,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginDto", loginDto);
            return "navegacion/login";
        }

        try {
            UsuarioSesionDto usuario = usuarioServicio.login(loginDto.getEmail(), loginDto.getContrasena());
            session.setAttribute("usuarioLogueado", usuario); // crea una sesión
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("loginDto", loginDto);
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "navegacion/login";
        } catch (Exception e) {
            model.addAttribute("loginDto", loginDto);
            model.addAttribute("error", "Ha ocurrido un error inesperado");
            return "navegacion/login";
        }
    }

    // Cierra sesión
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // destruye la sesión
        return "redirect:/";
    }
}
