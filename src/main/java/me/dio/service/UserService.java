package me.dio.service;

import me.dio.domain.model.User;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface de serviço para a entidade User.
 * Define os contratos para as operações relacionadas aos usuários.
 */
public interface UserService {


    /**
     * Cria um novo usuário.
     *
     * @param userToCreate Dados do usuário a ser criado.
     * @return User recém-criado.
     * @throws IllegalArgumentException se o email da conta já existir.
     */
    User create(User userToCreate) throws IllegalArgumentException;

    /**
     * Busca um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser buscado.
     * @return User correspondente ao ID fornecido.
     * @throws NoSuchElementException se o usuário não for encontrado.
     */
    User findById(Long id) throws NoSuchElementException;

    /**
     * Recupera todos os usuários.
     *
     * @return Lista de todos os usuários.
     */
    List<User> findAll();

    /**
     * Atualiza um usuário existente com os dados fornecidos.
     * O método preserva os dados existentes que não forem fornecidos.
     *
     * @param id ID do usuário a ser atualizado.
     * @param user Dados atualizados do usuário.
     * @return User atualizado.
     * @throws NoSuchElementException se o usuário não for encontrado.
     */
    User update(Long id, User user) throws NoSuchElementException;

    /**
     * Remove um usuário pelo ID.
     *
     * @param id ID do usuário a ser removido.
     * @throws NoSuchElementException se o usuário não for encontrado.
     */
    void delete(Long id) throws NoSuchElementException;
}
