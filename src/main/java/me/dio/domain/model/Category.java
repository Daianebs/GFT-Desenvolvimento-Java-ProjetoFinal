package me.dio.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * Entidade que representa uma categoria de tarefas no sistema.
 * Cada categoria possui um nome e uma lista de tarefas associadas.
 */
@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da categoria (e.g., "Pessoal", "Trabalho").
     */
    @Column(nullable = false)
    private String name;

    /**
     * Lista de tarefas associadas à categoria.
     * O mapeamento é unidirecional e as operações em cascata são aplicadas.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Esta é a referência "gerenciadora"
    private List<ToDo> todos;

    // Construtor padrão necessário para o JPA
    public Category() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(List<ToDo> todos) {
        this.todos = todos;
    }
}
