package com.gilmar.sistemainventario.domain.port.input;

import com.gilmar.sistemainventario.application.command.UpdateCategoryCommand;
import com.gilmar.sistemainventario.domain.model.Category;

public interface UpdateCategoryUseCase {

    Category update(Long id, UpdateCategoryCommand command);
}
