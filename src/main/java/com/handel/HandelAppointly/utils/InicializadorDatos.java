package com.handel.HandelAppointly.utils;

import com.handel.HandelAppointly.entidades.Divisa;
import com.handel.HandelAppointly.repositorios.DivisaRepositorio;
import com.handel.HandelAppointly.servicios.DivisaServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InicializadorDatos implements CommandLineRunner {

    private final DivisaRepositorio divisaRepositorio;
    private final DivisaServicio divisaServicio;


    @Override
    public void run(String... args) {
        if (divisaRepositorio.count() == 0) {
            List<Divisa> initialCurrencies = List.of(
                    new Divisa("USD", "$", "US Dollar", BigDecimal.ONE, null),
                    new Divisa("MXN", "$", "Mexican Peso", BigDecimal.ZERO, null),
                    new Divisa("EUR", "€", "Euro", BigDecimal.ZERO, null),
                    new Divisa("GBP", "£", "British Pound", BigDecimal.ZERO, null),
                    new Divisa("CAD", "$", "Canadian Dollar", BigDecimal.ZERO, null),
                    new Divisa("NZD", "$", "New Zealand Dollar", BigDecimal.ZERO, null),
                    new Divisa("CNY", "¥", "Chinese Renminbi", BigDecimal.ZERO, null),
                    new Divisa("JPY", "¥", "Japanese yen", BigDecimal.ZERO, null),
                    new Divisa("BRL", "R$", "Brazilian real", BigDecimal.ZERO, null),
                    new Divisa("INR", "₹", "Indian rupee", BigDecimal.ZERO, null)
            );

            divisaRepositorio.saveAll(initialCurrencies);

            log.info("Database Initialized: Basic currencies added");
            divisaServicio.updateTipoCambio();
        }
    }
}
