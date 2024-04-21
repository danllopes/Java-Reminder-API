package br.com.danllopes.reminderapi.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(

        @NotBlank
        String name

) {
}
