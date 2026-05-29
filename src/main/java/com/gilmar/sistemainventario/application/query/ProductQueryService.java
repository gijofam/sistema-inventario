package com.gilmar.sistemainventario.application.query;

import com.gilmar.sistemainventario.domain.exception.ResourceNotFoundException;
import com.gilmar.sistemainventario.domain.model.Product;
import com.gilmar.sistemainventario.domain.port.input.GetProductUseCase;
import com.gilmar.sistemainventario.domain.port.output.ProductRepositoryPort;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductQueryService implements GetProductUseCase {

    private final ProductRepositoryPort repositoryPort;

    public ProductQueryService(ProductRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Product getById(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
    }

    @Override
    public List<Product> listAll() {
        return repositoryPort.findAll();
    }
}
