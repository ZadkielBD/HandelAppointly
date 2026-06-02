package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.entidades.Doctor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "rol", expression = "java(com.handel.HandelAppointly.enums.Rol.DOCTOR)")
    @Mapping(target = "especialidades", ignore = true)
    @Mapping(target = "divisa", ignore = true)
    Doctor aEntidad(DoctorSolicitudDto solicitudDto);

    @Mapping(target = "especialidades", expression = "java(doctor.getEspecialidades().stream().map(e -> e.getNombre()).collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "codigoDivisa", source = "divisa.codigo")
    @Mapping(target = "simboloDivisa", source = "divisa.simbolo")
    DoctorRespuestaDto aRespuestaDto(Doctor doctor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "especialidades", ignore = true)
    @Mapping(target = "divisa", ignore = true)
    void actualizarDoctorDesdeDto(DoctorSolicitudDto dto, @MappingTarget Doctor entity);
}
