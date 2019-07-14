package com.customclearancecost.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "owner")
    private Owner owner;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "age")
    private Age age;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "yearOfManufacture")
    private YearOfManufacture yearOfManufacture;

    @Column(name = "ageCoefficient")
    private Integer ageCoefficient;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "engineType")
    private EngineType engineType;

    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "countryOfOrigin")
    private CountryOfOrigin countryOfOrigin;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currencyOfContract")
    private CurrencyOfContract currencyOfContract;

    @Column(name = "priceInCurrency")
    private Float priceInCurrency;


    // Taxes
    @Column(name = "impostBasis")
    private double impostBasis;

    @Column(name = "impostRate")
    private double impostRate;

    @Column(name = "impost")
    private double impost;


    @Column(name = "exciseBasis")
    private double exciseBasis;

    @Column(name = "exciseRate")
    private double exciseRate;

    @Column(name = "excise")
    private double excise;


    @Column(name = "VATBasis")
    private double VATBasis;

    @Column(name = "VATRate")
    private double VATRate;

    @Column(name = "VAT")
    private double VAT;
}