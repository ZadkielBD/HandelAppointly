package com.handel.HandelAppointly.dtos.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.handel.HandelAppointly.enums.Rol;

import java.time.LocalDate;

public record PacienteRespuestaDto(
        String nationalId,
        Long id,
        String nombre,
        String apellido,
        String email,
        String numeroTelefono,
        Rol rol,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String emergencyContactName,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String emergencyContactPhoneNumber
) { }
