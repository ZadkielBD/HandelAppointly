package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.DoctorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DoctorSolicitudDto;
import com.handel.HandelAppointly.entidades.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DoctorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "divisa", ignore = true)
    @Mapping(target = "especialidad", ignore = true)
    Doctor aEntidad(DoctorSolicitudDto solicitudDto);

    @Mapping(source = "especialidad.nombre", target = "especialidad")
    @Mapping(source = "divisa.codigo", target = "codigoDivisa")
    @Mapping(target = "precioUSD", ignore = true)
    DoctorRespuestaDto aRespuestaDto(Doctor doctor);
}
