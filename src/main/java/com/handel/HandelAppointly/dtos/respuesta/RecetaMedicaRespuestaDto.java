package com.handel.HandelAppointly.dtos.respuesta;

import lombok.Data;

@Data
public class RecetaMedicaRespuestaDto {
    private Long id;
    private String medicina;
    private String dosis;
    private Integer diasDuracion;
}
