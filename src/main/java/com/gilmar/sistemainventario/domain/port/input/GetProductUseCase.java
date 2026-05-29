package com.gilmar.sistemainventario.domain.port.input;

import com.gilmar.sistemainventario.domain.model.Product;
import java.util.List;

public interface GetProductUseCase {

    Product getById(Long id);

    List<Product> listAll();
}
