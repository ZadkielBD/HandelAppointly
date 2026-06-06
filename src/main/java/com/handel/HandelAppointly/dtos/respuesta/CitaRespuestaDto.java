package com.handel.HandelAppointly.dtos.respuesta;

import com.handel.HandelAppointly.enums.EstatusCita;
import com.handel.HandelAppointly.enums.TipoConsulta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CitaRespuestaDto {
    private Long id;
    private String codigoCita;
    private String paciente;
    private Long pacienteId;
    private String doctorNombre;
    private Long doctorId;
    private LocalDateTime fechaHora;
    private EstatusCita estatus;
    private TipoConsulta consulta;
    private String motivo;
    private BigDecimal precioUSD;
}
