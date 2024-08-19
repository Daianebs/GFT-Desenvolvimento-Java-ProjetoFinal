package me.dio.controller.dto;

import me.dio.domain.model.Category;
import jakarta.validation.constraints.NotEmpty;

/**
 * DTO para representar uma categoria.
 */
public record CategoryDto(
        @NotEmpty(message = "O nome da categoria não pode estar vazio.")
        String name
) {
    public CategoryDto(Category category) {
        this(category.getName());
    }

    public Category toModel() {
        Category category = new Category();
        category.setName(this.name);
        return category;
    }
}
