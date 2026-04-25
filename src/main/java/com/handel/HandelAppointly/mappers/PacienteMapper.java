package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.entidades.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PacienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "citas", ignore = true)
    Paciente aEntity(PacienteSolicitudDto solicitudDto);

    PacienteRespuestaDto aRespuestaDto(Paciente paciente);
}
