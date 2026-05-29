package com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierUpdateRequest(
    @NotBlank(message = "El nombre es obligatorio") String name,
    @NotBlank(message = "El nombre de contacto es obligatorio") String contactName,
    @NotBlank(message = "El correo electrónico es obligatorio") String email,
    @NotBlank(message = "El teléfono es obligatorio") 
    @Size(min = 9,max = 12, message = "El teléfono no puede exceder los 12 caracteres")
    String phone,

    String address
) {
    
}
