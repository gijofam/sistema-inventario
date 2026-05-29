package com.gilmar.sistemainventario.domain.port.input;

import com.gilmar.sistemainventario.application.command.AdjustStockCommand;
import com.gilmar.sistemainventario.domain.model.Product;

public interface AdjustStockUseCase {

    Product adjustStock(Long id, AdjustStockCommand command);
}
