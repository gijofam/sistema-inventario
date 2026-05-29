package com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto;

public record SupplierResponse(
    Long id,
    String name,
    String contactName,
    String email,
    String phone,
    String address  
){
   
}
