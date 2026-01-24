package com.pradolabs.ap.domain.port.in;

import com.pradolabs.ap.domain.model.response.CategoryResponse;
import reactor.core.publisher.Flux;

public interface ListCategoryUseCase {
    Flux<CategoryResponse> listCategories();
}
