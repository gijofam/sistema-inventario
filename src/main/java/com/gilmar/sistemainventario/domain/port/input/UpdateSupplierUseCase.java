package com.gilmar.sistemainventario.domain.port.input;

import com.gilmar.sistemainventario.application.command.UpdateSupplierCommand;
import com.gilmar.sistemainventario.domain.model.Supplier;

public interface UpdateSupplierUseCase {
    Supplier update( Long id, UpdateSupplierCommand command );
}
