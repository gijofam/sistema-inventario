package com.gilmar.sistemainventario.domain.port.input;

import com.gilmar.sistemainventario.application.command.CreateSupplierCommand;
import com.gilmar.sistemainventario.domain.model.Supplier;

public interface CreateSupplierUseCase {
    Supplier create( CreateSupplierCommand command );
}
