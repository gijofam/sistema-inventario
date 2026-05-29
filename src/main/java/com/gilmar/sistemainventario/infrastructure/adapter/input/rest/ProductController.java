package com.gilmar.sistemainventario.infrastructure.adapter.input.rest;

import com.gilmar.sistemainventario.application.command.ProductCommandService;
import com.gilmar.sistemainventario.application.query.ProductQueryService;
import com.gilmar.sistemainventario.domain.model.Product;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.ProductCreateRequest;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.ProductResponse;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.ProductUpdateRequest;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.StockAdjustmentRequest;
import com.gilmar.sistemainventario.infrastructure.mapper.ProductRestMapper;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductCommandService commandService;
    private final ProductQueryService queryService;

    public ProductController(ProductCommandService commandService, ProductQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest request) {
        Product product = commandService.create(ProductRestMapper.toCreateCommand(request));
        ProductResponse response = ProductRestMapper.toResponse(product);
        return ResponseEntity.created(URI.create("/api/v1/products/" + response.id())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listAll() {
        List<ProductResponse> response = queryService.listAll().stream()
                .map(ProductRestMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ProductRestMapper.toResponse(queryService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {
        Product product = commandService.update(id, ProductRestMapper.toUpdateCommand(request));
        return ResponseEntity.ok(ProductRestMapper.toResponse(product));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponse> adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustmentRequest request) {
        Product product = commandService.adjustStock(id, ProductRestMapper.toAdjustCommand(request));
        return ResponseEntity.ok(ProductRestMapper.toResponse(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
