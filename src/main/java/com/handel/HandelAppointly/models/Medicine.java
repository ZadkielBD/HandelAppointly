package com.handel.HandelAppointly.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "medicine")
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true ,nullable = false)
    private String name;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String formula;

    @Column(nullable = false, name = "stock_quantity")
    private Integer stock;

    @Column(nullable = false, name = "min_stock_quantity")
    private Integer minStockAlert;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private LocalDate expirationDate;
}
