package com.handel.HandelAppointly.controladores;

import com.handel.HandelAppointly.dtos.respuesta.CitaRespuestaDto;
import com.handel.HandelAppointly.dtos.respuesta.UsuarioSesionDto;
import com.handel.HandelAppointly.dtos.solicitud.PagoSolicitudDto;
import com.handel.HandelAppointly.enums.MetodoPago;
import com.handel.HandelAppointly.servicios.CitaServicio;
import com.handel.HandelAppointly.servicios.DivisaServicio;
import com.handel.HandelAppointly.servicios.PagoServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoControlador {

    private final PagoServicio pagoServicio;
    private final CitaServicio citaServicio;
    private final DivisaServicio divisaServicio;

    @GetMapping
    public String mostrarTodos(@PageableDefault(size = 10, sort = "fechaPago") Pageable pageable,
                               HttpSession session,
                               Model modelo) {
        UsuarioSesionDto usuario = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        modelo.addAttribute("pagos", pagoServicio.findByPacienteId(usuario.getId(), pageable));
        return "pago/pagos";
    }

    @GetMapping("/cita/{citaId}")
    public String mostrarPago(@PathVariable Long citaId,
                              HttpSession session,
                              Model modelo) {
        UsuarioSesionDto usuario = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        CitaRespuestaDto cita = citaServicio.findById(citaId);

        // Si ya está pagada, redirigir al detalle de la cita
        if (pagoServicio.existePagoPorCita(citaId)) {
            modelo.addAttribute("pago", pagoServicio.findByCitaId(citaId));
            modelo.addAttribute("cita", cita);
            return "pago/comprobante";
        }

        PagoSolicitudDto solicitud = new PagoSolicitudDto();
        solicitud.setCitaId(citaId);

        modelo.addAttribute("pago", solicitud);
        modelo.addAttribute("cita", cita);
        modelo.addAttribute("metodosPago", MetodoPago.values());
        modelo.addAttribute("divisas", divisaServicio.findAll());
        return "pago/pagar";
    }

    @PostMapping
    public String procesarPago(@Valid @ModelAttribute("pago") PagoSolicitudDto solicitudDto,
                               BindingResult result,
                               HttpSession session,
                               Model modelo,
                               RedirectAttributes redirectAttributes) {
        UsuarioSesionDto usuario = (UsuarioSesionDto) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        if (result.hasErrors()) {
            CitaRespuestaDto cita = citaServicio.findById(solicitudDto.getCitaId());
            modelo.addAttribute("cita", cita);
            modelo.addAttribute("metodosPago", MetodoPago.values());
            modelo.addAttribute("divisas", divisaServicio.findAll());
            return "pago/pagar";
        }

        try {
            pagoServicio.pagar(solicitudDto);
            redirectAttributes.addFlashAttribute("mensaje", "Pago realizado exitosamente");
            return "redirect:/pagos/cita/" + solicitudDto.getCitaId();
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/citas/" + solicitudDto.getCitaId();
        }
    }
}