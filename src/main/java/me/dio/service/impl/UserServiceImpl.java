package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementação do serviço para a entidade User.
 * Contém a lógica de negócios relacionada aos usuários.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Construtor para injeção do repositório de usuários.
     *
     * @param userRepository Repositório de usuários a ser injetado.
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User create(User userToCreate) throws IllegalArgumentException {
        if (userRepository.existsByEmail(userToCreate.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com o e-mail fornecido.");
        }
        return userRepository.save(userToCreate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findById(Long id) throws NoSuchElementException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o ID: " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User update(Long id, User user) throws NoSuchElementException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o ID: " + id));
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws NoSuchElementException {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
