package me.dio.domain.repository;

import me.dio.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para a entidade Category.
 * Este repositório permite realizar operações de CRUD e consultas relacionadas à entidade Category.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Método para buscar uma categoria pelo nome.
     * @param name Nome da categoria a ser buscada.
     * @return Category correspondente ao nome fornecido.
     */
    Category findByName(String name);
}
