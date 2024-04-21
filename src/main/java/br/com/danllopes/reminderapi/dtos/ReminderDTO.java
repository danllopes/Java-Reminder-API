package br.com.danllopes.reminderapi.dtos;

import br.com.danllopes.reminderapi.domain.enums.Priority;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReminderDTO(

        String title,

        String description,

        @NotNull
        LocalDate date,

        @NotNull
        LocalTime time,

        String category,

        @NotNull
        Priority priority

) {
}
