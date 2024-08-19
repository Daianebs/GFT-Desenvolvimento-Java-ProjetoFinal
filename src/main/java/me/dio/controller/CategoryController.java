package me.dio.controller;

import me.dio.domain.model.Category;
import me.dio.controller.dto.CategoryDto;
import me.dio.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Controlador REST para operações relacionadas a categorias.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Cria uma nova categoria.
     *
     * @param categoryDto Dados da categoria a serem criados.
     * @return Resposta com a categoria criada e o local do novo recurso.
     */
    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new category and return the created category's data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "409", description = "Category with the given name already exists")
    })
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto) {
        try {
            Category category = categoryDto.toModel();
            Category createdCategory = categoryService.create(category);
            URI location = URI.create(String.format("/api/categories/%s", createdCategory.getId()));
            return ResponseEntity.created(location).body(new CategoryDto(createdCategory));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CategoryDto(e.getMessage()));
        }
    }

    /**
     * Recupera uma categoria pelo ID.
     *
     * @param id ID da categoria a ser recuperada.
     * @return Resposta com a categoria encontrada ou um status de não encontrado.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Retrieve category details by category ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.findById(id);
            return ResponseEntity.ok(new CategoryDto(category));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Categoria não encontrada com o ID: " + id);
        }
    }

    /**
     * Recupera todas as categorias.
     *
     * @return Resposta com a lista de todas as categorias.
     */
    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieve all categories in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of categories")
    })
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(CategoryDto::new)
                .toList();
        return ResponseEntity.ok(categoryDtos);
    }

    /**
     * Atualiza uma categoria existente.
     *
     * @param id           ID da categoria a ser atualizada.
     * @param categoryDto  Dados atualizados da categoria.
     * @return Resposta com a categoria atualizada ou um status de não encontrado.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing category", description = "Update the category details by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid category data provided")
    })
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        try {
            // Converte DTO para entidade
            Category categoryToUpdate = categoryDto.toModel();
            // Atualiza a categoria no serviço
            Category updatedCategory = categoryService.update(id, categoryToUpdate);

            // Retorna a resposta com a categoria atualizada
            return ResponseEntity.ok(new CategoryDto(updatedCategory));
        } catch (NoSuchElementException e) {
            // Categoria não encontrada
            throw new NoSuchElementException("Categoria não encontrada com o ID: " + id);
        } catch (IllegalArgumentException e) {
            // Dados inválidos fornecidos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Remove uma categoria pelo ID.
     *
     * @param id ID da categoria a ser removida.
     * @return Resposta com status de remoção bem-sucedida ou não encontrado.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
