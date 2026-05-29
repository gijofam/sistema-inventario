package com.gilmar.sistemainventario.application.query;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilmar.sistemainventario.domain.exception.ResourceNotFoundException;
import com.gilmar.sistemainventario.domain.model.Supplier;
// import com.gilmar.sistemainventario.domain.port.input.GetSupplierUseCase;
import com.gilmar.sistemainventario.domain.port.output.SupplierRepositoryPort;



@Service
@Transactional(readOnly = true)
public class SupplierQueryService {

    private final SupplierRepositoryPort repositoryPort;

    public SupplierQueryService(SupplierRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    // @Override
    // public Supplier getById(Long id) {
    //     return repositoryPort.findById(id)
    //     .orElseThrow(() -> new ResourceNotFoundException(null, id));
    // }

    public Supplier getById(Long id) {
        return repositoryPort.findById(id)  
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor",id));
    }
    // @Override
    // public List<Supplier> listAll() {
    //     return repositoryPort.findAll();
    // }

    public List<Supplier> listAll() {
        return repositoryPort.findAll();
    }

    
}
