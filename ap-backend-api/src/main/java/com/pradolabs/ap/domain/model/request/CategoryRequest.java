package com.pradolabs.ap.domain.model.request;

import java.math.BigDecimal;

public record CategoryRequest(
    String nome,
    String alias,
    Integer percentual,
    BigDecimal valorSugerido,
    String descricao) {}
