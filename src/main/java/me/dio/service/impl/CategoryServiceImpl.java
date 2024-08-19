package me.dio.service.impl;

import me.dio.domain.model.Category;
import me.dio.domain.repository.CategoryRepository;
import me.dio.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementação do serviço para a entidade Category.
 * Contém a lógica de negócios relacionada às categorias.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Construtor para injeção do repositório de categorias.
     * @param categoryRepository Repositório de categorias a ser injetado.
     */
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category create(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Já existe uma categoria com o nome: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada com o ID: " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category update(Long id, Category category) {
        // Verifica se a categoria existe
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada com o ID: " + id));

        // Atualiza apenas o nome da categoria
        existingCategory.setName(category.getName());

        // Salva e retorna a categoria atualizada
        return categoryRepository.save(existingCategory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Categoria não encontrada com o ID: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
