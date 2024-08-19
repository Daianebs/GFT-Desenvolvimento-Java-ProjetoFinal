package me.dio.service.impl;

import me.dio.domain.model.Category;
import me.dio.domain.model.Status;
import me.dio.domain.model.ToDo;
import me.dio.domain.model.User;
import me.dio.domain.repository.CategoryRepository;
import me.dio.domain.repository.ToDoRepository;
import me.dio.domain.repository.UserRepository;
import me.dio.service.ToDoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementação do serviço para a entidade ToDo.
 * Contém a lógica de negócios relacionada às tarefas (ToDo).
 */
@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToDo create(String title, String description, Status status, String dueDate, Long userId, Long categoryId) {
        // Buscando o usuário pelo ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o ID: " + userId));

        // Buscando a categoria pelo ID
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada com o ID: " + categoryId));

        // Criando o DateTimeFormatter para o formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Analisando a data e convertendo para LocalDate
        LocalDate parsedDueDate = LocalDate.parse(dueDate, formatter);

        // Criando o ToDo
        ToDo toDo = new ToDo();
        toDo.setTitle(title);
        toDo.setDescription(description);
        toDo.setStatus(status);
        toDo.setDueDate(parsedDueDate.toString());  // Armazenando a data no formato padrão (yyyy-MM-dd)
        toDo.setUser(user);
        toDo.setCategory(category);

        // Salvando o ToDo no repositório
        return toDoRepository.save(toDo);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ToDo findById(Long id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada com o ID: " + id));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ToDo> findByUserId(Long userId) {
        return toDoRepository.findByUserId(userId);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ToDo> findByStatus(Status status) {
        return toDoRepository.findByStatus(status);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ToDo update(Long id, ToDo toDo) {
        if (!toDoRepository.existsById(id)) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + id);
        }
        toDo.setId(id);
        return toDoRepository.save(toDo);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        if (!toDoRepository.existsById(id)) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + id);
        }
        toDoRepository.deleteById(id);
    }
}
