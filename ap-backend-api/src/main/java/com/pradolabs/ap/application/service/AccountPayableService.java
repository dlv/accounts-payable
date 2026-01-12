package com.pradolabs.ap.application.service;

import com.pradolabs.ap.domain.model.entity.AccountPayable;
import com.pradolabs.ap.domain.model.request.AccountPayableResquest;
import com.pradolabs.ap.domain.model.response.AccountPayableResponse;
import com.pradolabs.ap.domain.port.in.CreateAccountPayableUseCase;
import com.pradolabs.ap.domain.repository.AccountPayableRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountPayableService implements CreateAccountPayableUseCase {

  private final AccountPayableRepository accountPayableRepository;

  public AccountPayableService(
      AccountPayableRepository accountPayableRepository) {
    this.accountPayableRepository = accountPayableRepository;
  }

  @Override
  public Mono<AccountPayableResponse> createAccountPayable(
      AccountPayableResquest request) {
    return accountPayableRepository
            .save(AccountPayable.from(request))
            .map(this::toResponse);
  }

  private AccountPayableResponse toResponse(AccountPayable accountPayable) {
    return new AccountPayableResponse(
        accountPayable.id(),
        accountPayable.description(),
        accountPayable.amount(),
        accountPayable.dueDate(),
        accountPayable.bills(),
        accountPayable.createdAt());
  }
}
