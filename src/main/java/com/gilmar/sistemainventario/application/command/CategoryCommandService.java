package com.gilmar.sistemainventario.application.command;

import com.gilmar.sistemainventario.domain.exception.BusinessRuleException;
import com.gilmar.sistemainventario.domain.exception.ResourceNotFoundException;
import com.gilmar.sistemainventario.domain.model.Category;
import com.gilmar.sistemainventario.domain.port.input.CreateCategoryUseCase;
import com.gilmar.sistemainventario.domain.port.input.DeleteCategoryUseCase;
import com.gilmar.sistemainventario.domain.port.input.UpdateCategoryUseCase;
import com.gilmar.sistemainventario.domain.port.output.CategoryRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryCommandService implements CreateCategoryUseCase, UpdateCategoryUseCase, DeleteCategoryUseCase {

    private final CategoryRepositoryPort repositoryPort;

    public CategoryCommandService(CategoryRepositoryPort repositoryPort) {
        this.repositoryPort= repositoryPort;
    }

    @Override
    public Category create(CreateCategoryCommand command) {
        if (repositoryPort.existsByName(command.name())) {
            throw new BusinessRuleException("Ya existe una categoría con ese nombre.");
        }

        Category category = new Category(
            null, 
            command.name(),
            command.description());
        // CategoryJpaEntity saved = repositoryPort.save(entity);
        
        return repositoryPort.save(category);
    }

    @Override
    public Category update(Long id, UpdateCategoryCommand command) {
        Category existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));

        existing.setName(command.name());
        existing.setDescription(command.description());

        // CategoryJpaEntity updated = repositoryPort.save(existing);

        // return CategoryPersistenceMapper.toDomain(updated);
        return repositoryPort.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (repositoryPort.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Categoría", id);
        }
        repositoryPort.deleteById(id);
    }
}
