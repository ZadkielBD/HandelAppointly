package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.DivisaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DivisaSolicitudDto;
import com.handel.HandelAppointly.entidades.Divisa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DivisaMapper {

    Divisa aEntidad(DivisaSolicitudDto divisaSolicitudDto);

    DivisaRespuestaDto aRespuesta(Divisa divisa);
}
