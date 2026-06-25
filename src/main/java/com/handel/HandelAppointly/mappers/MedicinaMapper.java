package com.handel.HandelAppointly.mappers;

import com.handel.HandelAppointly.dtos.respuesta.MedicinaRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.MedicinaSolicitudDto;
import com.handel.HandelAppointly.entidades.Medicina;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicinaMapper {

    Medicina aEntidad(MedicinaSolicitudDto solicitudDto);

    MedicinaRespuestaDto aRespuestaDto(Medicina entidad);

    void actualizarMedicina(MedicinaSolicitudDto solicitudDto, @MappingTarget Medicina entidad);
}
