package com.handel.HandelAppointly.client;

import com.handel.HandelAppointly.dtos.externo.FrankfurterRespuestaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FrankfurterClient implements CurrencyApiClient {
    private final RestTemplate restTemplate;

    private static final String URL = "https://api.frankfurter.app/latest?base=%s";

    @Override
    public FrankfurterRespuestaDto getExchangeRates(String base) {
        try {
            return restTemplate.getForObject(
                    String.format(URL, base),
                    FrankfurterRespuestaDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al llamar Frankfurter API ", e);
        }
    }
}
