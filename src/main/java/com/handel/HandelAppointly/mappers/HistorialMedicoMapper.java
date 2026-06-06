package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.HistorialMedicoRespuestaDto;
import com.handel.HandelAppointly.entidades.HistorialMedico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ConsultaMedicaMapper.class})
public interface HistorialMedicoMapper {
    @Mapping(target = "paciente", source = "paciente.nombre")
    HistorialMedicoRespuestaDto aRespuestaDto(HistorialMedico historialMedico);
}
