package com.handel.HandelAppointly.servicios;

import com.handel.HandelAppointly.dtos.solicitud.DivisaSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DivisaRespuestaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DivisaServicio {

    DivisaRespuestaDto add(DivisaSolicitudDto requestDto);

    DivisaRespuestaDto findByCode(String code);

    Page<DivisaRespuestaDto> findAll(Pageable pageable);

    void updateExchangeRates();

    void delete(String code);
}
