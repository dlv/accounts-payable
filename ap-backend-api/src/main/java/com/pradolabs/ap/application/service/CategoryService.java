package com.pradolabs.ap.application.service;

import com.pradolabs.ap.domain.model.entity.Category;
import com.pradolabs.ap.domain.model.request.CategoryRequest;
import com.pradolabs.ap.domain.model.response.CategoryResponse;
import com.pradolabs.ap.domain.port.in.CreateCategoryUseCase;
import com.pradolabs.ap.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CategoryService implements CreateCategoryUseCase {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Mono<CategoryResponse> createCategory(CategoryRequest request) {
    return validateRequest(request)
        .then(categoryRepository.save(Category.from(request)))
        .map(this::toResponse);
  }

  private Mono<Void> validateRequest(CategoryRequest request) {
    return Mono.defer(() -> {
      if (request.nome() == null || request.nome().isBlank()) {
        return Mono.error(
            new IllegalArgumentException("Nome is required"));
      }
      if (request.percentual() == null 
          || request.percentual() < 0 
          || request.percentual() > 100) {
        return Mono.error(new IllegalArgumentException(
            "Percentual must be between 0 and 100"));
      }
      if (request.valorSugerido() == null 
          || request.valorSugerido().signum() < 0) {
        return Mono.error(new IllegalArgumentException(
            "ValorSugerido must be positive"));
      }
      return Mono.empty();
    });
  }

  private CategoryResponse toResponse(Category category) {
    return new CategoryResponse(
        category.id(),
        category.name(),
        category.alias(),
        category.percentage(),
        category.suggestedValue(),
        category.description(),
        category.createdAt());
  }
}
