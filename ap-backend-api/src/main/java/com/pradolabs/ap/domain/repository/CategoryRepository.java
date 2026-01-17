package com.pradolabs.ap.domain.repository;

import com.pradolabs.ap.domain.model.entity.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository
    extends ReactiveCrudRepository<Category, Long> {}
