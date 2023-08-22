package be.technifutur.spring.demo.controller;

import be.technifutur.spring.demo.service.MessageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/message/last")
    @ApiResponse(
            description = "retourne la valeur de la variable message",
            responseCode = "200"
    )
    public ResponseEntity<String> getLastMessage(){
        String body = messageService.getLastMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.add( "headerName", "headerValue, headerValue2" );
        HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
//        return new ResponseEntity<>(body, headers, status);
        return ResponseEntity
                .status( status )
//                .headers(headers)
                .header("headerName", "value1", "value2")
//                .contentType( MediaType.APPLICATION_JSON )
                .body( body );
//                .build(); // dans ce cas: pas de body
    }

    @PostMapping({"/message", "/message/add"})
    public void addMessage(@RequestBody String toAdd){
        messageService.addMessage( toAdd );
    }


    // {pathVar:regex} :
    // - pathVar: permet de recouper l'info dans le segment d'URI (à utiliser avec @PathVariable)
    // - regex: permet de n'accepte la requete que si la pathVar correspond au regex
    @DeleteMapping("/message/{index:[0-9]+}")
    public void deleteMessage(@PathVariable("index") int i){
        messageService.deleteMessage(i);
    }


    @GetMapping("/message")
    public List<String> getAll(){
        return messageService.getMessages();
    }

    @GetMapping("/message/{index:[0-9]+}")
    public String getMessage( @PathVariable int index ){
        return messageService.getMessage(index);
    }

    @PutMapping("/message/{index:[0-9]+}")
    public void changeMessage( @PathVariable int index, @RequestBody String replacement ){
        messageService.changeMessage(index, replacement);
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/test/header")
    public void testGetHeader(@RequestHeader String testHeader){
        System.out.println(testHeader);
    }


}
