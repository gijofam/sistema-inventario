package com.gilmar.sistemainventario.application.command;

public record UpdateSupplierCommand(
    String name,
    String contactName,
    String email,
    String phone,
    String address
) {
    
}
