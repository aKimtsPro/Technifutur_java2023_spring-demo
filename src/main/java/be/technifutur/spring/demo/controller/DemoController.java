package be.technifutur.spring.demo.controller;

import be.technifutur.spring.demo.service.MessageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    private final MessageService messageService;

    public DemoController(MessageService messageService) {
        this.messageService = messageService;
    }

    // GET - http://localhost:8080/hello
    @GetMapping("/hello")
    public String helloWorld(){
        return "hello world!";
    }

    // GET - http://localhost:8080/addition
    @GetMapping("/addition")
    public int add(@RequestParam("membreA") int a, @RequestParam("membreB") int b){
        return a + b;
    }

    // ATTENTION la variable "message" met à mal le concept de STATELESSNESS
    @GetMapping("/message/last")
    @ApiResponse(
            description = "retourne la valeur de la variable message",
            responseCode = "200"
    )
    public String getMessage(){
        return messageService.getLastMessage();
    }

    @PostMapping({"/message", "/message/add"})
    public void addMessage(@RequestBody String toAdd){
        messageService.addMessage( toAdd );
    }


    // {pathVar:regex} :
    // - pathVar: permet de recouper l'info dans le segment d'URI (à utiliser avec @PathVariable)
    // - regex: permet de n'accepte la requete que si la pathVar correspond au regex
    @DeleteMapping("/message/{index:[09]+}")
    public void deleteMessage(@PathVariable("index") int i){
        messageService.deleteMessage(i);
    }




}
