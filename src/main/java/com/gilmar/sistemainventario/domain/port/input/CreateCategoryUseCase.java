package com.gilmar.sistemainventario.domain.port.input;

import com.gilmar.sistemainventario.application.command.CreateCategoryCommand;
import com.gilmar.sistemainventario.domain.model.Category;

public interface  CreateCategoryUseCase {
    
    Category create(CreateCategoryCommand command);

}
