package org.bob.dto;

import org.bob.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpnseResponseDTO(
        Long id,
        String description,
        BigDecimal amount,
        LocalDate date,
        String categoryName,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    public ExpnseResponseDTO(Expense expense) {
        this(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategory() != null ? expense.getCategory().getName() : null,
                expense.getCreatedAt(),
                expense.getUpdatAt()
        );
    }
}
