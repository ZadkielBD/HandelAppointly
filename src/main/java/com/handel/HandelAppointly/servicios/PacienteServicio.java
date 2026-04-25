package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteServicio {

    PacienteRespuestaDto create(PacienteSolicitudDto solicitudDto);

    PacienteRespuestaDto findById(Long id);

    Page<PacienteRespuestaDto> findAll(Pageable paginable);

    PacienteRespuestaDto update(Long id, PacienteSolicitudDto solicitudDto);

    PacienteRespuestaDto patch(Long id, PacienteSolicitudDto solicitudDto);

    void delete(Long id);
}
