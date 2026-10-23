package com.pizzaria.api.exceptions;

import com.pizzaria.api.dto_created.dto_erro.ErroCampoDTO;
import com.pizzaria.api.dto_created.dto_erro.ResponseErroExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ExceptionControllerHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ResponseErroExceptionDTO> erroExceptionDTOResponseEntity(RecursoNaoEncontradoException e,HttpServletRequest request ){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResponseErroExceptionDTO dto = new ResponseErroExceptionDTO(
                Instant.now(),
                status.value(),
                e.getMessage(),
                "Recurso não encontrado",
                request.getRequestURI(),
                List.of()



        );
        return ResponseEntity.status(status).body(dto);

        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public  ResponseEntity<ResponseErroExceptionDTO> erroExceptionDTOResponseEntityList(MethodArgumentNotValidException e,
                                                                                            HttpServletRequest request){
        List<ErroCampoDTO> erroCampo = e.getBindingResult()
                .getFieldErrors().stream().map(erro -> new ErroCampoDTO(erro.getField(), erro.getDefaultMessage())).toList();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ResponseErroExceptionDTO dto = new ResponseErroExceptionDTO(
                Instant.now(),
                status.value(),
                "Erro de Validação!",
                "Um ou vários campos com erro",
                request.getRequestURI(),
                erroCampo
        );
        return ResponseEntity.status(status).body(dto);

        }




}
