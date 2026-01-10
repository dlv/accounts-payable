package com.pradolabs.ap.domain.repository;

import com.pradolabs.ap.domain.model.entity.AccountPayable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPayableRepository
    extends ReactiveCrudRepository<AccountPayable, Long> {}
