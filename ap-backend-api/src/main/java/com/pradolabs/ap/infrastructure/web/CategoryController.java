package com.pradolabs.ap.infrastructure.web;

import com.pradolabs.ap.domain.model.request.CategoryRequest;
import com.pradolabs.ap.domain.model.response.CategoryResponse;
import com.pradolabs.ap.domain.port.in.CreateCategoryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/accounts-payable/categories")
public class CategoryController {

  private final CreateCategoryUseCase createCategoryUseCase;

  public CategoryController(CreateCategoryUseCase createCategoryUseCase) {
    this.createCategoryUseCase = createCategoryUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ResponseEntity<CategoryResponse>> create(
      @RequestBody CategoryRequest request) {

    return createCategoryUseCase
        .createCategory(request)
        .map(response -> ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
