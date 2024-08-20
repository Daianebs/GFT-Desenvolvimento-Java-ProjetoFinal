package me.dio.controller.dto;

import me.dio.domain.model.Category;

/**
 * DTO para representar uma categoria.
 */
public record CategoryDto(
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
