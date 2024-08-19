package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dio.domain.model.Status;
import me.dio.domain.model.ToDo;
import me.dio.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para gerenciamento de tarefas (ToDo).
 * Disponibiliza endpoints para a criação, atualização, busca e deleção de tarefas.
 */
@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    private final ToDoService toDoService;

    /**
     * Construtor para injeção do serviço de tarefas.
     * @param toDoService Serviço de tarefas a ser injetado.
     */
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    /**
     * Endpoint para criar uma nova tarefa.
     *
     * @param title Título da tarefa.
     * @param description Descrição detalhada da tarefa (opcional).
     * @param status Status da tarefa.
     * @param dueDate Data de vencimento da tarefa.
     * @param userId ID do usuário ao qual a tarefa será associada.
     * @param categoryId ID da categoria ao qual a tarefa será associada.
     * @return ResponseEntity contendo a tarefa recém-criada.
     */
    @PostMapping
    @Operation(summary = "Create a new Task", description = "Create a new ToDo and return the created ToDo's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ToDo data provided")
    })
    public ResponseEntity<ToDo> createToDo(
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam Status status,
            @RequestParam String dueDate,
            @RequestParam Long userId,
            @RequestParam Long categoryId) {

        // Chamando o serviço para criar a nova tarefa
        ToDo createdToDo = toDoService.create(title, description, status, dueDate, userId, categoryId);

        // Definindo a URI da nova tarefa criada
        URI location = URI.create(String.format("/api/todos/%s", createdToDo.getId()));

        // Retornando a resposta com a tarefa criada e o local onde ela pode ser acessada
        return ResponseEntity.created(location).body(createdToDo);
    }

    /**
     * Endpoint para buscar uma tarefa pelo ID.
     * @param id ID da tarefa a ser buscada.
     * @return ResponseEntity contendo a tarefa encontrada ou erro 404.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get Task by ID", description = "Retrieve Task details by task ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) {
        ToDo toDo = toDoService.findById(id);
        return ResponseEntity.ok(toDo);
    }

    /**
     * Endpoint para listar todas as tarefas de um usuário específico.
     * @param userId ID do usuário cujas tarefas serão listadas.
     * @return ResponseEntity contendo a lista de tarefas do usuário.
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get tasks by User ID", description = "Retrieve all ToDos associated with a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<List<ToDo>> findByUserId(@PathVariable Long userId) {
        List<ToDo> toDos = toDoService.findByUserId(userId);
        return ResponseEntity.ok(toDos);
    }

    /**
     * Endpoint para listar todas as tarefas com um status específico.
     * @param status Status das tarefas a serem listadas.
     * @return ResponseEntity contendo a lista de tarefas com o status fornecido.
     */
    @GetMapping("/status/{status}")
    @Operation(summary = "Get tasks by Status", description = "Retrieve all ToDos with a specific status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "404", description = "No tasks found with the specified status")
    })
    public ResponseEntity<List<ToDo>> findByStatus(@PathVariable Status status) {
        List<ToDo> toDos = toDoService.findByStatus(status);
        return ResponseEntity.ok(toDos);
    }

    /**
     * Endpoint para atualizar uma tarefa existente pelo ID.
     * @param id ID da tarefa a ser atualizada.
     * @param toDo Dados atualizados da tarefa.
     * @return ResponseEntity contendo a tarefa atualizada ou erro 404.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Task", description = "Update the details of a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "400", description = "Invalid task data provided")
    })
    public ResponseEntity<ToDo> updateToDo(@PathVariable Long id, @RequestBody ToDo toDo) {
        ToDo updatedToDo = toDoService.update(id, toDo);
        return ResponseEntity.ok(updatedToDo);
    }

    /**
     * Endpoint para deletar uma tarefa existente pelo ID.
     * @param id ID da tarefa a ser deletada.
     * @return ResponseEntity com status de sucesso ou erro 404.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Task", description = "Delete a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> deleteToDoById(@PathVariable Long id) {
        toDoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
