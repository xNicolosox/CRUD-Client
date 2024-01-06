package com.clientCrud.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestClient(
        Long id,

        @NotBlank
        String name,
        @NotNull
        String cpf,
        @NotNull
        String telefone,
        @NotNull
        String email

) {

}
