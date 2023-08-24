package be.technifutur.spring.demo.controller;

import be.technifutur.spring.demo.models.dto.GamerDTO;
import be.technifutur.spring.demo.models.entity.Gamer;
import be.technifutur.spring.demo.models.form.GamerForm;
import be.technifutur.spring.demo.service.GamerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gamer")
public class GamerController {

    private final GamerService gamerService;
    public GamerController(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @PostMapping
    public ResponseEntity<Long> addGamer(@RequestBody GamerForm form){
        return ResponseEntity.status( HttpStatus.CREATED )
                .body( gamerService.add(form.toEntity()) );
    }

    @GetMapping
    public ResponseEntity<List<GamerDTO>> getAll(){
        return ResponseEntity.ok(
                gamerService.getAll().stream()
                        .map(GamerDTO::toDTO)
                        .toList()
        );
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<GamerDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok( GamerDTO.toDTO(gamerService.getOne(id)) );
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody GamerForm form){
        gamerService.update( id, form.toEntity() );
        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        gamerService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

    // PATCH - http://localhost:8080/gamer/1/add_game?gameId=1
    @PatchMapping("/{gamerId:[0-9]+}/add_game")
    public ResponseEntity<?> addGame(@PathVariable Long gamerId, @RequestParam Long gameId ){
        gamerService.addGame(gamerId, gameId);
        return ResponseEntity.noContent()
                .build();
    }
}
