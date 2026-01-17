package com.pradolabs.ap.domain.port.in;

import com.pradolabs.ap.domain.model.request.CategoryRequest;
import com.pradolabs.ap.domain.model.response.CategoryResponse;
import reactor.core.publisher.Mono;

public interface CreateCategoryUseCase {
  Mono<CategoryResponse> createCategory(CategoryRequest request);
}
