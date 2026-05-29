package com.gilmar.sistemainventario.domain.port.input;

import com.gilmar.sistemainventario.application.command.CreateProductCommand;
import com.gilmar.sistemainventario.domain.model.Product;

public interface CreateProductUseCase {

    Product create(CreateProductCommand command);
}
