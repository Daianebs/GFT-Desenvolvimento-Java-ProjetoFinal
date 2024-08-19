package me.dio.controller;

import me.dio.domain.model.User;
import me.dio.controller.dto.UserDto;
import me.dio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Controlador REST para operações relacionadas a usuários.
 * Este controlador expõe endpoints para criar, ler, atualizar e excluir usuários.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Cria um novo usuário.
     *
     * @param userDto Dados do usuário a serem criados.
     * @return Resposta com o usuário criado e o local do novo recurso.
     */
    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        User user = userDto.toModel();
        User createdUser = userService.create(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(new UserDto(createdUser));
    }

    /**
     * Recupera um usuário pelo ID.
     *
     * @param id ID do usuário a ser recuperado.
     * @return Resposta com o usuário encontrado ou um status de não encontrado.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve user details by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(new UserDto(user));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }
    }
    /**
     * Recupera todos os usuários.
     *
     * @return Resposta com a lista de todos os usuários.
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No users found")
    })
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<UserDto> userDtos = users.stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userDtos);
        }
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param id ID do usuário a ser atualizado.
     * @param userDto Dados atualizados do usuário.
     * @return Resposta com o usuário atualizado ou um status de não encontrado.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user", description = "Update the user details by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        try {
            User user = userDto.toModel();
            user.setId(id);
            User updatedUser = userService.update(id, user);
            return ResponseEntity.ok(new UserDto(updatedUser));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Remove um usuário pelo ID.
     *
     * @param id ID do usuário a ser removido.
     * @return Resposta com status de remoção bem-sucedida ou não encontrado.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }
    }
}
