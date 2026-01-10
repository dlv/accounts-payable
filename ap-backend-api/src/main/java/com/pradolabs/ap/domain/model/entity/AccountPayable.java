package com.pradolabs.ap.domain.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.pradolabs.ap.domain.model.request.AccountPayableResquest;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("accounts_payable")
public record AccountPayable(
    @Id Long id,
    String description,
    BigDecimal amount,
    LocalDate dueDate,
    String bills,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {

    public static AccountPayable from(AccountPayableResquest resquest) {
        return new AccountPayable(
                null,
                resquest.descricao(),
                resquest.valor(),
                resquest.vencimento(),
                resquest.categoria(),
                LocalDateTime.now(),
                null
        );
    }
}
