package me.dio.domain.repository;

import me.dio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para a entidade User.
 * Este repositório permite realizar operações de CRUD e consultas relacionadas à entidade User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Método para verificar se já existe um usuário com o e-mail fornecido.
     * @param email e-mail a ser verificado.
     * @return true se já existir um usuário com o e-mail fornecido, false caso contrário.
     */
    boolean existsByEmail(String email);

    /**
     * Método para buscar um usuário por seu nome de usuário.
     * @param username Nome de usuário a ser buscado.
     * @return User correspondente ao nome de usuário fornecido.
     */
    User findByUsername(String username);

    /**
     * Método para buscar um usuário por seu email.
     * @param email Email a ser buscado.
     * @return User correspondente ao email fornecido.
     */
    User findByEmail(String email);
}
