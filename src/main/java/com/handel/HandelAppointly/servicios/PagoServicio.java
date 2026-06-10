package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.respuesta.PagoRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.PagoSolicitudDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagoServicio {

    PagoRespuestaDto pagar(PagoSolicitudDto solicitudDto);

    PagoRespuestaDto findByCitaId(Long citaId);

    boolean existePagoPorCita(Long citaId);

    Page<PagoRespuestaDto> findByPacienteId(Long pacienteId, Pageable pageable);
}
