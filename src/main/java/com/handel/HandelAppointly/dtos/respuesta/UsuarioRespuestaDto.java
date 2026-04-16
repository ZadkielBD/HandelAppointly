package com.handel.HandelAppointly.dtos.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.handel.HandelAppointly.enums.Rol;

import java.time.LocalDate;

public record UsuarioRespuestaDto(
        Long id,
        String nombre,
        String apellido,
        String email,
        String numeroTelefono,
        Rol rol
) {}
