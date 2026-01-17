package com.pradolabs.ap.domain.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CategoryResponse(
    Long id,
    String nome,
    String alias,
    Integer percentual,
    BigDecimal valorSugerido,
    String descricao,
    LocalDateTime createdAt) {}
