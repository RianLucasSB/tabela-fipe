package com.boas.rian.tabela.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleData(
        @JsonAlias("Marca")
        String brand,
        @JsonAlias("Modelo")
        String model,
        @JsonAlias("AnoModelo")
        String year,
        @JsonAlias("CodigoFipe")
        String fipeCode,
        @JsonAlias("Valor")
        String value
) {}
