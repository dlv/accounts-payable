package com.pradolabs.ap.domain.model.entity;

import com.pradolabs.ap.domain.model.request.CategoryRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("categories")
public record Category(
    @Id Long id,
    String name,
    String alias,
    Integer percentage,
    BigDecimal suggestedValue,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {

  public static Category from(CategoryRequest request) {
    return new Category(
        null,
        request.nome(),
        request.alias(),
        request.percentual(),
        request.valorSugerido(),
        request.descricao(),
        LocalDateTime.now(),
        null);
  }
}
