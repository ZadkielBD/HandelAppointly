package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.EspecialidadRespuestaDto;
import java.util.List;

public interface EspecialidadServicio {
    List<EspecialidadRespuestaDto> findAll();
    EspecialidadRespuestaDto findById(Long id);
    EspecialidadRespuestaDto create(String nombre, String descripcion);
    EspecialidadRespuestaDto update(Long id, String nombre, String descripcion);
    void delete(Long id);
}
