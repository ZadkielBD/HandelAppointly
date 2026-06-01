package com.handel.HandelAppointly.dtos.solicitud;

import com.handel.HandelAppointly.enums.TipoConsulta;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaSolicitudDto {

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El ID del doctor es obligatorio")
    private Long doctorId;

//    @NotBlank(message = "El motivo de la cita no puede estar vacío")
//    private Long consultaMedica;

    @NotBlank(message = "El motivo no puede estar en blanco")
    private String motivo;

//    @NotNull(message = "El estatus de cita es obligatorio")
//    private EstatusCita estatus;

    @NotNull(message = "El tipo de consulta es obligatorio")
    private TipoConsulta consulta;

    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La cita debe ser programada en una fecha futura")
    private LocalDateTime fechaHora;
}
