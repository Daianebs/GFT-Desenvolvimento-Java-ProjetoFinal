package me.dio.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * Classe global para tratamento de exceções em toda a aplicação.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Tratamento de exceção para quando um recurso não é encontrado.
     * @param ex Exceção NoSuchElementException.
     * @return ResponseEntity com status 404 e a mensagem de erro específica.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        // Loga a mensagem original da exceção
        logger.error("Erro de elemento não encontrado: ", ex);
        //retorna mensagem genérica
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Tratamento de exceção para erros de argumentos inválidos.
     * @param ex Exceção IllegalArgumentException.
     * @return ResponseEntity com status 400 e a mensagem de erro específica.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Erro de argumento inválido: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Tratamento geral de exceções inesperadas.
     * @param ex Exceção genérica.
     * @return ResponseEntity com status 500 e uma mensagem genérica de erro.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedException(Exception ex) {
        logger.error("Erro inesperado no servidor: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado no servidor. Verifique o log.");
    }
}
