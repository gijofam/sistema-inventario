package com.gilmar.sistemainventario.application.command;

public record CreateSupplierCommand(
    String name,
    String contactName,
    String email,
    String phone,
    String address
) {
}