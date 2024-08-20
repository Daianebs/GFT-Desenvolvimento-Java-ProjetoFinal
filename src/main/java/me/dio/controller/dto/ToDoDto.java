package me.dio.controller.dto;

import me.dio.domain.model.Status;
import me.dio.domain.model.ToDo;

/**
 * DTO para representar uma tarefa (ToDo).
 */
public record ToDoDto(
        Long id,
        String title,
        String description,
        Status status,
        String dueDate,
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