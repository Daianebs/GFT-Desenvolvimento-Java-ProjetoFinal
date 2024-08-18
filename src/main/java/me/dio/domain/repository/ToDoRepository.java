package me.dio.domain.repository;

import me.dio.domain.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para a entidade ToDo.
 * Este repositório permite realizar operações de CRUD e consultas relacionadas à entidade ToDo.
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    /**
     * Método para buscar todas as tarefas associadas a um usuário.
     * @param userId ID do usuário cujas tarefas serão buscadas.
     * @return Lista de ToDo associadas ao usuário.
     */
    List<ToDo> findByUserId(Long userId);

    /**
     * Método para buscar todas as tarefas associadas a uma categoria.
     * @param categoryId ID da categoria cujas tarefas serão buscadas.
     * @return Lista de ToDo associadas à categoria.
     */
    List<ToDo> findByCategoryId(Long categoryId);
}

