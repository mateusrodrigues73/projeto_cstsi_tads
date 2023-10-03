package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.infra.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class TratadorDeErros extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity trataErro400(ConstraintViolationException ex){
        var erros = ex.getConstraintViolations();
        return ResponseEntity.badRequest().body(erros.stream().map(ErroValidation::new).toList());
    }

    private record ErroValidation(String campo, String mensagem) {
        public ErroValidation(ConstraintViolation<?> erro){
            this(erro.getPropertyPath().toString(), erro.getMessage());
        }
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity trataErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity trataErro400() {
        return ResponseEntity.badRequest().build();
    }
}