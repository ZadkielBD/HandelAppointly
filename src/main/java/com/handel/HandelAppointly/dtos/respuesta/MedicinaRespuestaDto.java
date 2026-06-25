package com.handel.HandelAppointly.dtos.respuesta;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicinaRespuestaDto {
    private String nombre;
    private String fabricante;
    private String formula;
    private Integer existencias;
    private Integer alertaExistenciaMinima;
    private String unidad;
    private LocalDate fechaExpiracion;
}
