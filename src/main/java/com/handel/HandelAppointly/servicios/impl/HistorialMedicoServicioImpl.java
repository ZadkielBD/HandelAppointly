package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.ConsultaMedicaRespuestaDto;
import com.handel.HandelAppointly.dtos.respuesta.HistorialMedicoRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.ConsultaMedicaSolicitudDto;
import com.handel.HandelAppointly.entidades.*;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.ConsultaMedicaMapper;
import com.handel.HandelAppointly.mappers.HistorialMedicoMapper;
import com.handel.HandelAppointly.repositorios.*;
import com.handel.HandelAppointly.servicios.HistorialMedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistorialMedicoServicioImpl implements HistorialMedicoServicio {

    private final HistorialMedicoRepositorio historialMedicoRepositorio;
    private final ConsultaMedicaRepositorio consultaMedicaRepositorio;
    private final MedicinaRepositorio medicinaRepositorio;
    private final HistorialMedicoMapper historialMedicoMapper;
    private final ConsultaMedicaMapper consultaMedicaMapper;

    @Override
    @Transactional(readOnly = true)
    public HistorialMedicoRespuestaDto findByPacienteId(Long pacienteId) {
        HistorialMedico historial = historialMedicoRepositorio.findByPacienteId(pacienteId)
                .orElse(null);
        if (historial == null) return null;

        return historialMedicoMapper.aRespuestaDto(historial);
    }

    @Override
    @Transactional
    public ConsultaMedicaRespuestaDto llenarConsulta(Long consultaId, ConsultaMedicaSolicitudDto solicitudDto) {
        ConsultaMedica consulta = consultaMedicaRepositorio.findById(consultaId)
                .orElseThrow(() -> new ResourcesNotFoundException("Consulta no encontrada"));

        consulta.setDiagnostico(solicitudDto.getDiagnostico());
        consulta.setSintomas(solicitudDto.getSintomas());
        consulta.setTratamiento(solicitudDto.getTratamiento());

        if (solicitudDto.getMedicinaId() != null) {
            Medicina medicina = medicinaRepositorio.findById(solicitudDto.getMedicinaId())
                    .orElseThrow(() -> new ResourcesNotFoundException("Medicina no encontrada"));

            if (consulta.getRecetaMedica() != null) {
                consulta.getRecetaMedica().setMedicinaId(medicina);
                consulta.getRecetaMedica().setDosis(solicitudDto.getDosis());
                consulta.getRecetaMedica().setDiasDuracion(solicitudDto.getDiasDuracion());
            } else {
                RecetaMedica receta = RecetaMedica.builder()
                        .consultaMedica(consulta)
                        .medicinaId(medicina)
                        .dosis(solicitudDto.getDosis())
                        .diasDuracion(solicitudDto.getDiasDuracion())
                        .build();
                consulta.setRecetaMedica(receta);
            }
        }

        consultaMedicaRepositorio.save(consulta);
        return consultaMedicaMapper.aRespuestaDto(consulta);
    }

    @Override
    @Transactional
    public ConsultaMedicaRespuestaDto actualizarConsulta(Long consultaId, ConsultaMedicaSolicitudDto solicitudDto) {
        ConsultaMedica consulta = consultaMedicaRepositorio.findById(consultaId)
                .orElseThrow(() -> new ResourcesNotFoundException("Consulta no encontrada"));

        consulta.setDiagnostico(solicitudDto.getDiagnostico());
        consulta.setSintomas(solicitudDto.getSintomas());
        consulta.setTratamiento(solicitudDto.getTratamiento());

        if (solicitudDto.getMedicinaId() != null) {
            Medicina medicina = medicinaRepositorio.findById(solicitudDto.getMedicinaId())
                    .orElseThrow(() -> new ResourcesNotFoundException("Medicina no encontrada"));

            if (consulta.getRecetaMedica() != null) {
                consulta.getRecetaMedica().setMedicinaId(medicina);
                consulta.getRecetaMedica().setDosis(solicitudDto.getDosis());
                consulta.getRecetaMedica().setDiasDuracion(solicitudDto.getDiasDuracion());
            } else {
                RecetaMedica receta = RecetaMedica.builder()
                        .consultaMedica(consulta)
                        .medicinaId(medicina)
                        .dosis(solicitudDto.getDosis())
                        .diasDuracion(solicitudDto.getDiasDuracion())
                        .build();
                consulta.setRecetaMedica(receta);
            }
        }

        consultaMedicaRepositorio.save(consulta);
        return consultaMedicaMapper.aRespuestaDto(consulta);
    }

    @Override
    @Transactional
    public void inicializarConsultaParaCita(Cita cita, Paciente paciente, Doctor doctor) {
        HistorialMedico historial = historialMedicoRepositorio
                .findByPacienteId(paciente.getId())
                .orElseGet(() -> historialMedicoRepositorio.save(
                        HistorialMedico.builder().paciente(paciente).build()
                ));

        ConsultaMedica consulta = ConsultaMedica.builder()
                .cita(cita)
                .doctor(doctor)
                .paciente(paciente)
                .historialMedico(historial)
                .build();

        consultaMedicaRepositorio.save(consulta);
    }
}