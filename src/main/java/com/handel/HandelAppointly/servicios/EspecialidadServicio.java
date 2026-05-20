package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.EspecialidadRespuestaDto;
import java.util.List;

public interface EspecialidadServicio {
    List<EspecialidadRespuestaDto> findAll();
}
