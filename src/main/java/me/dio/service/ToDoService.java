package me.dio.service;

import me.dio.domain.model.Status;
import me.dio.domain.model.ToDo;

import java.util.List;

/**
 * Interface de serviço para a entidade ToDo.
 * Define os contratos para as operações relacionadas às tarefas (ToDo).
 */
public interface ToDoService {

    /**
     * Cria uma nova tarefa associada a um usuário e uma categoria.
     *
     * @param title Título da tarefa.
     * @param description Descrição detalhada da tarefa.
     * @param status Status da tarefa.
     * @param dueDate Data de vencimento da tarefa.
     * @param userId ID do usuário ao qual a tarefa será associada.
     * @param categoryId ID da categoria ao qual a tarefa será associada.
     * @return ToDo recém-criada.
     */
    ToDo create(String title, String description, Status status, String dueDate, Long userId, Long categoryId);

    /**
     * Busca uma tarefa pelo seu ID.
     *
     * @param id ID da tarefa a ser buscada.
     * @return ToDo correspondente ao ID fornecido.
     */
    ToDo findById(Long id);

    /**
     * Retorna uma lista de todas as tarefas associadas a um usuário específico.
     *
     * @param userId ID do usuário cujas tarefas serão listadas.
     * @return Lista de tarefas associadas ao usuário.
     */
    List<ToDo> findByUserId(Long userId);

    /**
     * Retorna uma lista de todas as tarefas com um status específico.
     *
     * @param status Status das tarefas a serem listadas.
     * @return Lista de tarefas que possuem o status fornecido.
     */
    List<ToDo> findByStatus(Status status);

    /**
     * Atualiza uma tarefa existente.
     *
     * @param id ID da tarefa a ser atualizada.
     * @param toDo Dados atualizados da tarefa.
     * @return ToDo atualizada.
     */
    ToDo update(Long id, ToDo toDo);

    /**
     * Deleta uma tarefa existente pelo ID.
     *
     * @param id ID da tarefa a ser deletada.
     */
    void delete(Long id);
}
