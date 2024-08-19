package me.dio.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import me.dio.domain.model.Status;
import me.dio.domain.model.ToDo;

/**
 * DTO para representar uma tarefa (ToDo).
 */
public record ToDoDto(
        Long id,
        @NotEmpty(message = "O título da tarefa não pode estar vazio.")
        String title,
        String description,
        @NotNull(message = "O status da tarefa não pode ser nulo.")
        Status status,
        @NotNull(message = "A data de vencimento não pode estar vazia.")
        String dueDate,
        @NotNull(message = "O ID do usuário não pode ser nulo.")
        Long userId,
        Long categoryId
) {
    public ToDoDto(ToDo toDo) {
        this(
                toDo.getId(),
                toDo.getTitle(),
                toDo.getDescription(),
                toDo.getStatus(),
                toDo.getDueDate(),
                toDo.getUser().getId(),
                toDo.getCategory() != null ? toDo.getCategory().getId() : null
        );
    }

    public ToDo toModel() {
        ToDo toDo = new ToDo();
        toDo.setTitle(this.title);
        toDo.setDescription(this.description);
        toDo.setStatus(this.status);
        toDo.setDueDate(this.dueDate);
        return toDo;
    }
}