package com.gilmar.sistemainventario.infrastructure.mapper;

import com.gilmar.sistemainventario.application.command.CreateCategoryCommand;
import com.gilmar.sistemainventario.application.command.UpdateCategoryCommand;
import com.gilmar.sistemainventario.domain.model.Category;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.CategoryCreateRequest;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.CategoryResponse;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.CategoryUpdateRequest;

public class CategoryRestMapper {

    public static CreateCategoryCommand toCreateCommand(CategoryCreateRequest request) {
        return new CreateCategoryCommand(request.name(), request.description());
    }

    public static UpdateCategoryCommand toUpdateCommand(CategoryUpdateRequest request) {
        return new UpdateCategoryCommand(request.name(), request.description());
    }

    public static CategoryResponse toResponse(Category category) {
        if (category == null) return null;
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
