package be.technifutur.spring.demo.controller;

import be.technifutur.spring.demo.exceptions.ResourceAlreadyLinkedException;
import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.exceptions.UniqueViolationException;
import be.technifutur.spring.demo.models.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;


// Permet une gestion d'erreur sur tous les controllers
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceNotFoundException ex, HttpServletRequest req){
        String uri = req.getRequestURI();
        String method = req.getMethod();

        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("id", ex.getId());
        error.put("resourceType", ex.getResourceClass().getSimpleName());

        ErrorDTO body = ErrorDTO.builder()
                .uri( uri )
                .method( method )
                .errors( Set.of(error) )
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(ResourceAlreadyLinkedException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceAlreadyLinkedException ex, HttpServletRequest req){
        String uri = req.getRequestURI();
        String method = req.getMethod();

        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("containerType", ex.getContainingClazz().getSimpleName());
        error.put("containerId", ex.getContainingId());
        error.put("containedType", ex.getContainedClazz().getSimpleName());
        error.put("containedId", ex.getContainedId());

        ErrorDTO body = ErrorDTO.builder()
                .uri( uri )
                .method( method )
                .errors( Set.of(error) )
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(body);
    }

    @ExceptionHandler(UniqueViolationException.class)
    public ResponseEntity<ErrorDTO> handle(UniqueViolationException ex, HttpServletRequest req){
        String uri = req.getRequestURI();
        String method = req.getMethod();

        Set<Map<String, Object>> errors = new LinkedHashSet<>();
        ex.getFieldNames().forEach(
                field -> {
                    Map<String, Object> errorData = new HashMap<>();
                    errorData.put(field, "should be unique");
                    errors.add(errorData);
                }
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        ErrorDTO.builder()
                                .uri( uri )
                                .method( method )
                                .errors( errors )
                                .build()
                );

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpServletRequest req = ((ServletWebRequest)request).getRequest();

        String uri = req.getRequestURI();
        String method = req.getMethod();

        Set<Map<String, Object>> errors = new LinkedHashSet<>();

        ex.getAllErrors().forEach( error -> {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("message", error.getDefaultMessage());
            errors.add( errorData );
        });

        return ResponseEntity.badRequest()
                .body(
                        ErrorDTO.builder()
                                .uri( uri )
                                .method( method )
                                .errors( errors )
                                .build()
                );
    }
}
