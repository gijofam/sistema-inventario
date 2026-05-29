package com.gilmar.sistemainventario.application.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilmar.sistemainventario.domain.exception.BusinessRuleException;
import com.gilmar.sistemainventario.domain.model.Supplier;
import com.gilmar.sistemainventario.domain.port.input.CreateSupplierUseCase;
import com.gilmar.sistemainventario.domain.port.input.DeleteSupplierUseCase;
import com.gilmar.sistemainventario.domain.port.input.UpdateSupplierUseCase;
import com.gilmar.sistemainventario.domain.port.output.SupplierRepositoryPort;



@Service
@Transactional 
public class SupplierCommandService implements CreateSupplierUseCase, UpdateSupplierUseCase, DeleteSupplierUseCase {
    private final SupplierRepositoryPort repositoryPort;

    // Inyectamos el puerto de salida a través del constructor
    public SupplierCommandService(SupplierRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }   

    @Override
    public Supplier create(CreateSupplierCommand command) {
    // 1. Validamos las reglas de negocio únicas que definimos en el repositorio
        if (repositoryPort.existsByName(command.name())) {
            throw new BusinessRuleException("Ya existe un proveedor registrado con ese nombre.");
            
        }

        if (repositoryPort.existsByEmail(command.email())) {
            throw new BusinessRuleException("Ya existe un proveedor registrado con ese correo electrónico.");
        }
    // 2. Instanciamos nuestro modelo de dominio puro (aquí adentro se ejecutan tus métodos de validación)
        Supplier supplier = new Supplier(
            null,
            command.name(),
            command.contactName(),
            command.email(),
            command.phone(),
            command.address()
        );

     // 3. Lo mandamos a guardar a través del puerto. El adaptador hará la magia con Hibernate.    
        return repositoryPort.save(supplier);
    }

    @Override
    public Supplier update(Long id, UpdateSupplierCommand command) {
        Supplier existing = repositoryPort.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Proveedor no encontrado con ID: " + id));

        if (!existing.getName().equalsIgnoreCase(command.name()) && repositoryPort.existsByName(command.name())) {
            throw new BusinessRuleException("Ya existe un proveedor registrado con ese nombre.");
        }

        if (!existing.getEmail().equalsIgnoreCase(command.email()) && repositoryPort.existsByEmail(command.email())) {
            throw new BusinessRuleException("Ya existe un proveedor registrado con ese correo electrónico.");
        }

        existing.setName(command.name());
        existing.setContactName(command.contactName());
        existing.setEmail(command.email());
        existing.setPhone(command.phone());
        existing.setAddress(command.address());

        return repositoryPort.save(existing);
    }

    @Override
    public void delete(Long id) {
        // Supplier existing = repositoryPort.findById(id)
        //         .orElseThrow(() -> new BusinessRuleException("Proveedor no encontrado con ID: " + id));

          // 1. Verificamos si existe antes de borrar
        if (repositoryPort.findById(id).isEmpty()) {
            throw new BusinessRuleException("Proveedor" + id);
        }

        // 2. Ejecutamos la eliminación física en PostgreSQL (Sin poner la palabra 'return')
        repositoryPort.deleteById(id);
    }


}
