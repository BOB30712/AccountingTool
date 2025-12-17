package org.bob.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseRequest(
        @NotBlank String description,
        @Positive BigDecimal amount,
        @PastOrPresent LocalDate date,
        @NotBlank String category
        ) {
}
