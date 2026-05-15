package com.handel.HandelAppointly.dtos.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.handel.HandelAppointly.enums.Rol;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteRespuestaDto{
    Long id;
    String nombre;
    String apellido;
    String email;
    String numeroTelefono;
    Rol rol;

    LocalDate fechaNacimiento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String emergencyContactName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String emergencyContactPhoneNumber;
}
