package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.RecetaMedicaRespuestaDto;
import com.handel.HandelAppointly.entidades.RecetaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecetaMedicaMapper {
    @Mapping(target = "medicina", source = "medicinaId.nombre")
    RecetaMedicaRespuestaDto aRespuestaDto(RecetaMedica recetaMedica);
}
