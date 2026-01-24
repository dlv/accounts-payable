package com.pradolabs.ap.domain.model.response;

import com.pradolabs.ap.domain.model.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CategoryResponse(
    Long id,
    String nome,
    String alias,
    Integer percentual,
    BigDecimal valorSugerido,
    String descricao,
    LocalDateTime createdAt) {

    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.id(),
                category.name(),
                category.alias(),
                category.percentage(),
                category.suggestedValue(),
                category.description(),
                category.createdAt());
    }
}
