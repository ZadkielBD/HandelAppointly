package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.AdministradorRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.AdministradorSolicitudDto;
import com.handel.HandelAppointly.entidades.Administrador;
import com.handel.HandelAppointly.entidades.Doctor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface AdministradorMapper {

    @Mapping(target = "id", ignore = true)
    Administrador aEntidad(AdministradorSolicitudDto solicitudDto);

    AdministradorRespuestaDto aRespuestaDto(Administrador administrador);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "rol", ignore = true)
    void actualizarDoctorDesdeDto(AdministradorSolicitudDto solicitudDto, @MappingTarget Administrador administrador);

}
