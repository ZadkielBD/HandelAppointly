package com.handel.HandelAppointly.client;

import com.handel.HandelAppointly.dtos.externo.FrankfurterRespuestaDto;

public interface CurrencyApiClient {
    FrankfurterRespuestaDto getExchangeRates(String base);
}
