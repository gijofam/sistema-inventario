package com.gilmar.sistemainventario.application.command;

public record CreateCategoryCommand(
        String name,
        String description
) {
}
