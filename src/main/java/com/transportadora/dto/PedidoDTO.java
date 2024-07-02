package com.transportadora.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record PedidoDTO(
        @JsonProperty("ïd") Long id,
        @NotBlank @NotNull @Length(min = 5, max = 100) String nome,
        String cpf,
        String logradouro,
        String volume,
        String mangueira,
        String valor,
        String status
) {
}
