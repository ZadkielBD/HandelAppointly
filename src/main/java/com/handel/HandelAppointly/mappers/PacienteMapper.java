package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.PacienteRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.dtos.solicitud.PacienteSolicitudDto;
import com.handel.HandelAppointly.entidades.Doctor;
import com.handel.HandelAppointly.entidades.Paciente;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "citas", ignore = true)
    Paciente aEntidad(PacienteSolicitudDto solicitudDto);

    PacienteRespuestaDto aRespuestaDto(Paciente paciente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "rol", ignore = true)
    void actualizarEntidadDesdeDto(PacienteSolicitudDto dto, @MappingTarget Paciente entity);
}
