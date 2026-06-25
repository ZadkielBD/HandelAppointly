package com.handel.HandelAppointly.dtos.solicitud;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicinaSolicitudDto {
    private String nombre;
    private String fabricante;
    private String formula;
    private Integer existencias;
    private Integer alertaExistenciaMinima;
    private String unidad;
    private LocalDate fechaExpiracion;
}
