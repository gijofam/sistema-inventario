package com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierCreateRequest(
    @NotBlank(message = "El nombre del proveedor es obligatorio") String name,
    @NotBlank(message = "El nombre de contacto es obligatorio") String contactName,
    @NotBlank(message = "El correo electrónico de contacto es obligatorio") String email,
    @NotBlank(message = "El teléfono es obligatorio") 
    @Size(min = 9,max = 12, message = "El teléfono no puede exceder los 12 caracteres")
    String phone,
    // @NotBlank(message = "La dirección es obligatoria") String address;
    String address
) {
    
}
