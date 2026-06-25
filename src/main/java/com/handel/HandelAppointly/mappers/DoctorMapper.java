package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.entidades.Doctor;
import com.handel.HandelAppointly.entidades.Especialidad;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "especialidades", ignore = true)
    @Mapping(target = "divisa", ignore = true)
    Doctor aEntidad(DoctorSolicitudDto solicitudDto);

    @Mapping(target = "especialidades", source = "especialidades")
    @Mapping(target = "codigoDivisa", source = "divisa.codigo")
    @Mapping(target = "simboloDivisa", source = "divisa.simbolo")
    DoctorRespuestaDto aRespuestaDto(Doctor entidad);

    default String mapEspecialidades(Especialidad especialidad) {
        return especialidad.getNombre();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "especialidades", ignore = true)
    @Mapping(target = "divisa", ignore = true)
    void actualizarDoctor(DoctorSolicitudDto solicitudDto, @MappingTarget Doctor entidad);
}
