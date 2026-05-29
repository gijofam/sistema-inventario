package com.gilmar.sistemainventario.infrastructure.mapper;

import com.gilmar.sistemainventario.application.command.CreateSupplierCommand;
import com.gilmar.sistemainventario.application.command.UpdateSupplierCommand;
import com.gilmar.sistemainventario.domain.model.Supplier;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.SupplierCreateRequest;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.SupplierResponse;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.SupplierUpdateRequest;

public class SupplierRestMapper {
    
    public static CreateSupplierCommand toCreateCommand(SupplierCreateRequest request) {
        if (request == null) return null;
        
        return new CreateSupplierCommand(
                request.name(),
                request.contactName(),
                request.email(),
                request.phone(),
                request.address()
        );
    }       

    public static UpdateSupplierCommand toUpdateCommand(SupplierUpdateRequest request) {
        if (request == null) return null;

        return new UpdateSupplierCommand(
                request.name(),
                request.contactName(),
                request.email(),
                request.phone(),
                request.address()
        );
    }

    public static SupplierResponse toResponse(Supplier supplier) {
        if (supplier == null) return null;

        return new SupplierResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getContactName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getAddress()
        );
    }   
}
