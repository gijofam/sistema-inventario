package com.gilmar.sistemainventario.infrastructure.adapter.input.rest;

import com.gilmar.sistemainventario.application.command.CategoryCommandService;
import com.gilmar.sistemainventario.application.query.CategoryQueryService;
import com.gilmar.sistemainventario.domain.model.Category;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.CategoryCreateRequest;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.CategoryResponse;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.CategoryUpdateRequest;
import com.gilmar.sistemainventario.infrastructure.mapper.CategoryRestMapper;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryCommandService commandService;
    private final CategoryQueryService queryService;

    public CategoryController(CategoryCommandService commandService, CategoryQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryCreateRequest request) {
        Category category = commandService.create(CategoryRestMapper.toCreateCommand(request));
        CategoryResponse response = CategoryRestMapper.toResponse(category);
        return ResponseEntity.created(URI.create("/api/v1/categories/" + response.id())).body(response);
    }




    @GetMapping
    public ResponseEntity<List<CategoryResponse>> listAll() {
        List<CategoryResponse> response = queryService.listAll().stream()
                .map(CategoryRestMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(CategoryRestMapper.toResponse(queryService.getById(id)));
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryUpdateRequest request) {
    //     Category category = commandService.commandService.update(id, CategoryRestMapper.toUpdateCommand(request));
    //     return ResponseEntity.ok(CategoryRestMapper.toResponse(category));
    // }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryUpdateRequest request) {
        Category category = commandService.update(id, CategoryRestMapper.toUpdateCommand(request));
        return ResponseEntity.ok(CategoryRestMapper.toResponse(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
