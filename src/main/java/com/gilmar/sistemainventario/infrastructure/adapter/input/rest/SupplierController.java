package com.gilmar.sistemainventario.infrastructure.adapter.input.rest;


import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gilmar.sistemainventario.domain.model.Supplier;
import com.gilmar.sistemainventario.application.command.SupplierCommandService; 
import com.gilmar.sistemainventario.application.query.SupplierQueryService;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.SupplierCreateRequest;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.SupplierUpdateRequest;
import com.gilmar.sistemainventario.infrastructure.mapper.SupplierRestMapper;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.SupplierResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierCommandService commandService;
    private final SupplierQueryService queryService;

    public SupplierController(SupplierCommandService commandService, SupplierQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@Valid @RequestBody SupplierCreateRequest request) {
        Supplier supplier = commandService.create(SupplierRestMapper.toCreateCommand(request));
        SupplierResponse response = SupplierRestMapper.toResponse(supplier);
        return ResponseEntity.created(URI.create("/api/v1/suppliers/" + response.id())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> listAll() {
        List<SupplierResponse> response = queryService.listAll().stream()
                .map(SupplierRestMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(SupplierRestMapper.toResponse(queryService.getById(id)));
    }       

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> update(@PathVariable Long id, @Valid @RequestBody SupplierUpdateRequest request) {
        Supplier supplier = commandService.update(id, SupplierRestMapper.toUpdateCommand(request));
        return ResponseEntity.ok(SupplierRestMapper.toResponse(supplier));
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
