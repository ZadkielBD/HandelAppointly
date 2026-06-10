package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.ConsultaMedicaRespuestaDto;
import com.handel.HandelAppointly.entidades.ConsultaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {RecetaMedicaMapper.class}
)
public interface ConsultaMedicaMapper {

    @Mapping(target = "doctorNombre", source = "doctor.nombre")
    @Mapping(target = "codigoCita", source = "cita.codigoCita")
    @Mapping(target = "receta", source = "recetaMedica")
    ConsultaMedicaRespuestaDto aRespuestaDto(ConsultaMedica consultaMedica);
}
