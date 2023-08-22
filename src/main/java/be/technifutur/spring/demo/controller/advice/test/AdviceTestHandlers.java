package be.technifutur.spring.demo.controller.advice.test;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("be.technifutur.spring.demo.controller.advice.test")
//@ControllerAdvice(basePackages = {"be.technifutur.spring.demo.controller.advice.test"})
public class AdviceTestHandlers {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handle(){
        return ResponseEntity.badRequest()
                .body("ce @ControllerAdvice ne fonctionne que pour les @Controller ou @RestController" +
                        " du package 'be.technifutur.spring.demo.controller.advice.test'");
    }

}
