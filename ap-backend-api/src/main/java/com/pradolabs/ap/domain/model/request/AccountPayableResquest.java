package com.pradolabs.ap.domain.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AccountPayableResquest(
        String descricao,
        BigDecimal valor,
        LocalDate vencimento,
        String categoria
) {
}
