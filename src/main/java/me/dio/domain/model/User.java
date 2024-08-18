package me.dio.domain.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidade que representa um usuário no sistema.
 * Um usuário possui um identificador único, nome de usuário, email, senha,
 * e uma lista de tarefas (ToDo) associadas.
 */
@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome de usuário do sistema.
     * O comprimento máximo é de 27 caracteres.
     */
    @Column(length = 27, nullable = false)
    private String username;

    /**
     * Email do usuário.
     * Deve ser único no sistema.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Senha do usuário.
     * Deve ser armazenada em formato seguro (hashed).
     */
    @Column(nullable = false)
    private String password;

    /**
     * Lista de tarefas (ToDo) associadas ao usuário.
     * O mapeamento é unidirecional e as operações em cascata são aplicadas.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDo> todos;

    // Construtor padrão necessário para o JPA
    public User() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(List<ToDo> todos) {
        this.todos = todos;
    }
}
