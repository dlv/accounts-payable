package com.pradolabs.ap.infrastructure.web;

import com.pradolabs.ap.domain.model.request.AccountPayableResquest;
import com.pradolabs.ap.domain.model.response.AccountPayableResponse;
import com.pradolabs.ap.domain.port.in.CreateAccountPayableUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/accounts-payable")
public class AccountPayableController {

  private final CreateAccountPayableUseCase createAccountPayableUseCase;

  public AccountPayableController(
      CreateAccountPayableUseCase createAccountPayableUseCase) {
    this.createAccountPayableUseCase = createAccountPayableUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ResponseEntity<AccountPayableResponse>> create(
      @RequestBody AccountPayableResquest accountPayable) {

    return createAccountPayableUseCase
            .createAccountPayable(accountPayable)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
