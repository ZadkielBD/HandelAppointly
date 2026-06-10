package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.PagoRespuestaDto;
import com.handel.HandelAppointly.entidades.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    @Mapping(target = "citaId", source = "cita.id")
    @Mapping(target = "codigoCita", source = "cita.codigoCita")
    @Mapping(target = "divisa", source = "divisa.codigo")
    PagoRespuestaDto aRespuestaDto(Pago pago);
}