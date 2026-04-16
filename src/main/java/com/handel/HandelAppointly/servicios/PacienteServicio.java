package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteServicio {

    PacienteRespuestaDto create(PacienteSolicitudDto requestDto);

    PacienteRespuestaDto findById(Long id);

    Page<PacienteRespuestaDto> findAll(Pageable pageable);

    PacienteRespuestaDto update(Long id, PacienteSolicitudDto requestDto);

    PacienteRespuestaDto patch(Long id, PacienteSolicitudDto requestDto);

    void delete(Long id);
}
