package com.boas.rian.tabela.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CommomData(
        @JsonAlias("codigo")
        String code,
        @JsonAlias("nome")
        String name
) {
}
