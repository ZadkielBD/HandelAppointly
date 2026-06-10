package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.dtos.respuesta.PagoRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.PagoSolicitudDto;
import com.handel.HandelAppointly.entidades.Cita;
import com.handel.HandelAppointly.entidades.Divisa;
import com.handel.HandelAppointly.entidades.Pago;
import com.handel.HandelAppointly.enums.EstadoPago;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.mappers.PagoMapper;
import com.handel.HandelAppointly.repositorios.CitaRepositorio;
import com.handel.HandelAppointly.repositorios.DivisaRepositorio;
import com.handel.HandelAppointly.repositorios.PagoRepositorio;
import com.handel.HandelAppointly.servicios.PagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class PagoServicioImpl implements PagoServicio {

    private final PagoRepositorio pagoRepositorio;
    private final CitaRepositorio citaRepositorio;
    private final DivisaRepositorio divisaRepositorio;
    private final PagoMapper pagoMapper;

    @Override
    @Transactional
    public PagoRespuestaDto pagar(PagoSolicitudDto solicitudDto) {
        if (pagoRepositorio.existsByCitaId(solicitudDto.getCitaId())) {
            throw new IllegalStateException("Esta cita ya tiene un pago registrado");
        }

        Cita cita = citaRepositorio.findById(solicitudDto.getCitaId())
                .orElseThrow(() -> new ResourcesNotFoundException("Cita no encontrada"));

        Divisa divisa = divisaRepositorio.findById(solicitudDto.getCodigoDivisa())
                .orElseThrow(() -> new ResourcesNotFoundException("Divisa no encontrada"));

        // precioLocal del doctor convertido a la divisa seleccionada
        // precioLocal está en la divisa propia del doctor; convertimos a USD primero,
        // luego al target. Si la divisa del doctor es la misma que la elegida, es directo.
        BigDecimal precioLocal = cita.getDoctor().getPrecioLocal();
        BigDecimal tipoCambioDoctor = cita.getDoctor().getDivisa().getTipoCambio(); // divisa del doctor vs USD
        BigDecimal precioEnUSD = precioLocal.divide(tipoCambioDoctor, 10, RoundingMode.HALF_UP);
        BigDecimal monto = precioEnUSD.multiply(divisa.getTipoCambio())
                .setScale(2, RoundingMode.HALF_UP);

        Pago pago = Pago.builder()
                .cita(cita)
                .monto(monto)
                .divisa(divisa)
                .metodoPago(solicitudDto.getMetodoPago())
                .estadoPago(EstadoPago.COMPLETADO)
                .build();

        return pagoMapper.aRespuestaDto(pagoRepositorio.save(pago));
    }

    @Override
    @Transactional(readOnly = true)
    public PagoRespuestaDto findByCitaId(Long citaId) {
        return pagoRepositorio.findByCitaId(citaId)
                .map(pagoMapper::aRespuestaDto)
                .orElse(null);
    }

    @Override
    public boolean existePagoPorCita(Long citaId) {
        return pagoRepositorio.existsByCitaId(citaId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PagoRespuestaDto> findByPacienteId(Long pacienteId, Pageable pageable) {
        return pagoRepositorio.findByCitaPacienteId(pacienteId, pageable)
                .map(pagoMapper::aRespuestaDto);
    }
}