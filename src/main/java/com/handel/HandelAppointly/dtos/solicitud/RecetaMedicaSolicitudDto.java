package com.handel.HandelAppointly.dtos.solicitud;

import lombok.Data;

@Data
public class RecetaMedicaSolicitudDto {
    private String medicina;
    private String dosis;
    private Integer diasDuracion;
}
