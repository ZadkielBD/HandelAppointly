package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.solicitud.DivisaSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DivisaRespuestaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DivisaServicio {

    DivisaRespuestaDto create(DivisaSolicitudDto solicitudDto);

    DivisaRespuestaDto findByCodigo(String codigo);

    List<DivisaRespuestaDto> findAll();

    Page<DivisaRespuestaDto> findAll(Pageable paginable);

    void updateTipoCambio();

    void delete(String codigo);
}
