package com.pradolabs.ap.domain.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AccountPayableResponse(
    Long id,
    String descricao,
    BigDecimal valor,
    LocalDate dataVencimento,
    String categoria,
    LocalDateTime dataCriacao
) {}
