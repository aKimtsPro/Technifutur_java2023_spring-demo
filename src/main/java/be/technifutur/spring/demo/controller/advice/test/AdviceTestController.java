package be.technifutur.spring.demo.controller.advice.test;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advice/test")
public class AdviceTestController {

    @GetMapping("/break")
    public void breakThis(){
        throw new ResourceNotFoundException(0L, Object.class);
    }

}
