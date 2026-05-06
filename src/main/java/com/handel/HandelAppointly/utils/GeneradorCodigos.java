package com.handel.HandelAppointly.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class GeneradorCodigos {
    public static String generateAppointmentCode() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(formatter);
        String id = UUID.randomUUID().toString().substring(0,4).toUpperCase();
        return "APT-" + formattedDate + "-" + id;
    }
}
