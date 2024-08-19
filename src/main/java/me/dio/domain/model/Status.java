package me.dio.domain.model;

/**
 * Enum que representa o status de uma tarefa.
 * Os possíveis valores são:
 * - PENDING: Tarefa ainda não iniciada.
 * - IN_PROGRESS: Tarefa em andamento.
 * - COMPLETED: Tarefa concluída.
 * - CANCELLED: Tarefa cancelada.
 */
public enum Status {
    PENDING,      // Tarefa pendente
    IN_PROGRESS,  // Tarefa em andamento
    COMPLETED,    // Tarefa concluída
    CANCELLED     // Tarefa cancelada
}
