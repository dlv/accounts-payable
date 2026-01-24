package com.pradolabs.ap.infrastructure.web;

import com.pradolabs.ap.domain.model.request.CategoryRequest;
import com.pradolabs.ap.domain.model.response.CategoryResponse;
import com.pradolabs.ap.domain.port.in.CreateCategoryUseCase;
import com.pradolabs.ap.domain.port.in.ListCategoryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts-payable/categories")
public class CategoryController {

  private final CreateCategoryUseCase createCategoryUseCase;
  private final ListCategoryUseCase listCategoryUseCase;

  public CategoryController(CreateCategoryUseCase createCategoryUseCase, ListCategoryUseCase listCategoryUseCase) {
    this.createCategoryUseCase = createCategoryUseCase;
      this.listCategoryUseCase = listCategoryUseCase;
  }

  @PostMapping
  // @ResponseStatus(HttpStatus.CREATED)
  public Mono<ResponseEntity<CategoryResponse>> create(
      @RequestBody CategoryRequest request) {

    return createCategoryUseCase
        .createCategory(request)
        .map(response -> ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping
  public Mono<ResponseEntity<List<CategoryResponse>>> getCategories() {

    return listCategoryUseCase.listCategories()
            .collectList()
            .map(ResponseEntity::ok);
  }
}
