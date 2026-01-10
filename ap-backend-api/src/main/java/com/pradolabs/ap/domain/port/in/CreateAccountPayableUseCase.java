package com.pradolabs.ap.domain.port.in;

import com.pradolabs.ap.domain.model.request.AccountPayableResquest;
import com.pradolabs.ap.domain.model.response.AccountPayableResponse;
import reactor.core.publisher.Mono;

public interface CreateAccountPayableUseCase {
  Mono<AccountPayableResponse> createAccountPayable(
      AccountPayableResquest request);
}
