package me.dio.controller.dto;

import me.dio.domain.model.User;
import jakarta.validation.constraints.Email;

/**
 * DTO para representar um usuário.
 * Este DTO é utilizado para transferir dados de usuário entre o cliente e o servidor.
 */
public record UserDto(
        Long id,
        String username,
        @Email(message = "O e-mail deve ser válido.")
        String email,
        String password
) {
    /**
     * Construtor que cria um UserDto a partir de um objeto User.
     *
     * @param user Objeto User a ser convertido.
     */
    public UserDto(User user) {
        this(
                user.getId(),            // Incluindo o ID do usuário
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    /**
     * Converte este DTO para um modelo User.
     *
     * @return Um novo objeto User com dados deste DTO.
     */
    public User toModel() {
        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }
}
