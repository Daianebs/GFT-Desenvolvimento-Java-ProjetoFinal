package me.dio.domain.repository;

import me.dio.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
/**
 * Repositório para a entidade Category.
 * Fornece métodos para operações CRUD em categorias.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Método para buscar uma categoria pelo nome.
     * @param name Nome da categoria a ser buscada.
     * @return Optional contendo a Categoria correspondente ao nome fornecido.
     */
    Optional<Category> findByName(String name);

    /**
     * Método para buscar uma categoria pelo ID.
     * @param id ID da categoria a ser buscada.
     * @return Optional contendo a Categoria correspondente ao ID fornecido.
     */
    Optional<Category> findById(Long id);
    /**
     * Verifica se uma categoria com o nome fornecido já existe.
     * @param name Nome da categoria.
     * @return true se uma categoria com o nome já existir, false caso contrário.
     */
    boolean existsByName(String name);
}
