package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.solicitud.DivisaSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DivisaRespuestaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DivisaServicio {

    DivisaRespuestaDto add(DivisaSolicitudDto solicitudDto);

    DivisaRespuestaDto findByCodigo(String codigo);

    Page<DivisaRespuestaDto> findAll(Pageable paginable);

    void updateTipoCambio();

    void delete(String codigo);
}
