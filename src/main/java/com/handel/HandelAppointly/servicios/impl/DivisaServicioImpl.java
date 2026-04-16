package com.handel.HandelAppointly.servicios.impl;

import com.handel.HandelAppointly.client.CurrencyApiClient;
import com.handel.HandelAppointly.dtos.externo.FrankfurterRespuestaDto;
import com.handel.HandelAppointly.dtos.solicitud.DivisaSolicitudDto;
import com.handel.HandelAppointly.dtos.respuesta.DivisaRespuestaDto;
import com.handel.HandelAppointly.entidades.Divisa;
import com.handel.HandelAppointly.excepciones.ResourcesNotFoundException;
import com.handel.HandelAppointly.repositorios.DivisaRepositorio;
import com.handel.HandelAppointly.servicios.DivisaServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DivisaServicioImpl implements DivisaServicio {
    private final DivisaRepositorio divisaRepositorio;
    private final CurrencyApiClient currencyApiClient;
    private final ModelMapper modelMapper;

    @Override
    public DivisaRespuestaDto add(DivisaSolicitudDto requestDto) {
        Divisa divisa = modelMapper.map(requestDto, Divisa.class);

        Divisa createdDivisa = divisaRepositorio.save(divisa);

        return modelMapper.map(createdDivisa, DivisaRespuestaDto.class);
    }

    @Override
    public DivisaRespuestaDto findByCode(String code) {
        Divisa divisa = findCurrencyById(code);
        return modelMapper.map(divisa, DivisaRespuestaDto.class);
    }

    @Override
    public Page<DivisaRespuestaDto> findAll(Pageable pageable) {
        return divisaRepositorio.findAll(pageable)
                .map(p -> modelMapper.map(p, DivisaRespuestaDto.class));
    }

    //Actualizacion a las 12am
    @Override
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void updateExchangeRates() {
        try {
            FrankfurterRespuestaDto response = currencyApiClient.getExchangeRates("USD");

            if (response == null || response.rates() == null) {
                log.error("The response is empty");
                return;
            }

            List<Divisa> updatedCurrencies = new ArrayList<>();

            response.rates().forEach((code, rate) ->
                divisaRepositorio.findById(code).ifPresent(divisa -> {
                    divisa.setTipoCambio(rate);
                    divisa.setUltimaActualizacion(LocalDateTime.now());
                    updatedCurrencies.add(divisa);
                })
            );

            divisaRepositorio.saveAll(updatedCurrencies);

            log.info("Currencies updated successfully at {}", LocalDateTime.now());

        } catch (Exception e) {
            log.error("Error updating currency rates ", e);
        }
    }

    @Override
    public void delete(String code) {
        Divisa divisa = findCurrencyById(code);
        divisaRepositorio.delete(divisa);
    }

    private Divisa findCurrencyById(String code) {
        return divisaRepositorio.findById(code)
                .orElseThrow(() -> new ResourcesNotFoundException("User with code " + code + " not found"));
    }
}
