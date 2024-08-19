package me.dio.service;

import me.dio.domain.model.Category;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para a entidade Category.
 * Define os contratos para as operações relacionadas às categorias.
 */
public interface CategoryService {

    /**
     * Busca uma categoria pelo seu ID.
     * @param id ID da categoria a ser buscada.
     * @return Category correspondente ao ID fornecido.
     */
    Category findById(Long id);

    /**
     * Cria uma nova categoria se não existir.
     * @param category Categoria a ser criada.
     * @return Categoria recém-criada.
     */
    Category create(Category category);

    /**
     * Atualiza uma categoria existente. Caso a categoria não seja encontrada, lança uma exceção.
     * Atualiza apenas o nome da categoria, preservando as tarefas associadas.
     *
     * @param id       ID da categoria a ser atualizada.
     * @param category Categoria com os dados atualizados.
     * @return Categoria atualizada.
     */
    Category update(Long id, Category category);

    /**
     * Remove uma categoria pelo seu ID.
     * @param id ID da categoria a ser removida.
     */
    void delete(Long id);

    /**
     * Retorna uma lista de todas as categorias.
     * @return Lista de todas as categorias.
     */
    List<Category> findAll();

    /**
     * Busca uma categoria pelo seu nome.
     *
     * @param name Nome da categoria a ser buscada.
     * @return Categoria correspondente ao nome fornecido.
     */
    Optional<Category> findByName(String name);
}
