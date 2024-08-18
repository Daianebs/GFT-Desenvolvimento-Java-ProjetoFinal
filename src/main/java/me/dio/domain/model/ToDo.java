package me.dio.domain.model;

import jakarta.persistence.*;

/**
 * Entidade que representa uma tarefa (ToDo) no sistema.
 * Cada tarefa possui um título, descrição, status, data de vencimento,
 * e está associada a um usuário e uma categoria.
 */
@Entity
@Table(name = "tb_todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título da tarefa.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Descrição detalhada da tarefa.
     */
    @Column
    private String description;

    /**
     * Status atual da tarefa (e.g., PENDING, COMPLETED).
     */
    @Column(nullable = false)
    private String status;

    /**
     * Data de vencimento da tarefa.
     */
    @Column(nullable = false)
    private String dueDate;

    /**
     * Usuário ao qual a tarefa está associada.
     * Representa uma relação muitos-para-um (ManyToOne).
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Categoria da tarefa.
     * Representa uma relação muitos-para-um (ManyToOne).
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Construtor padrão necessário para o JPA
    public ToDo() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
