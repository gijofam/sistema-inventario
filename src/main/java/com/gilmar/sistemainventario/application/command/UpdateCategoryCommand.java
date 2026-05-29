package com.gilmar.sistemainventario.application.command;

public record UpdateCategoryCommand(
        String name,
        String description
) {
}
