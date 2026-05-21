package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.entidades.Doctor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "divisa", ignore = true)
    @Mapping(target = "especialidades", ignore = true)
    Doctor aEntidad(DoctorSolicitudDto solicitudDto);

    @Mapping(source = "divisa.codigo", target = "codigoDivisa")
    @Mapping(target = "precioUSD", ignore = true)
    DoctorRespuestaDto aRespuestaDto(Doctor doctor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "rol", ignore = true)
    void actualizarDoctorDesdeDto(DoctorSolicitudDto solicitudDto, @MappingTarget Doctor doctor);
}
