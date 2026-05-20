package com.handel.HandelAppointly.dtos.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EspecialidadRespuestaDto {
    private Long id;
    private String nombre;
    private String descripcion;
}
