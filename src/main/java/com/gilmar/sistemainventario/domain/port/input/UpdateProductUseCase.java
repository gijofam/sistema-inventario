package com.gilmar.sistemainventario.domain.port.input;

import com.gilmar.sistemainventario.application.command.UpdateProductCommand;
import com.gilmar.sistemainventario.domain.model.Product;

public interface UpdateProductUseCase {

    Product update(Long id, UpdateProductCommand command);
}
