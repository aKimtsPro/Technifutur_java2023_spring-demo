package be.technifutur.spring.demo.controller;

import be.technifutur.spring.demo.models.dto.GameDTO;
import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Studio;
import be.technifutur.spring.demo.models.form.GameForm;
import be.technifutur.spring.demo.models.form.GamePlatformsForm;
import be.technifutur.spring.demo.models.form.GamePriceForm;
import be.technifutur.spring.demo.service.GameService;
import be.technifutur.spring.demo.service.StudioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final StudioService studioService;

    public GameController(GameService gameService, StudioService studioService) {
        this.gameService = gameService;
        this.studioService = studioService;
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<GameDTO> getOne(@PathVariable long id){
        Game game = gameService.getGame(id);
        GameDTO body = GameDTO.toDTO(game);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAll(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ){
        List<Game> games = gameService.getAllGames(minPrice, maxPrice);
        List<GameDTO> body = games.stream()
                .map(GameDTO::toDTO)
                .toList();
        return ResponseEntity.ok( body );
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<GameDTO> delete(@PathVariable long id){
        Game game = gameService.getGame(id);
        GameDTO body = GameDTO.toDTO(game);
        gameService.removeGame(id);
        return ResponseEntity.ok( body );
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody GameForm form){
        Game entity = form.toEntity();
        Studio studio = studioService.getOne( form.getStudioId() );
        entity.setStudio( studio );

        long body = gameService.addGame( entity );
        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( body );
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<GameDTO> update(@PathVariable long id, @RequestBody GameForm form){
        Game entity = form.toEntity();
        Studio studio = studioService.getOne( form.getStudioId() );
        entity.setStudio( studio );

        Game game = gameService.updateGame( id, entity );
        GameDTO body = GameDTO.toDTO( game );
        return ResponseEntity.ok( body );
    }

    @PatchMapping("/{id:[0-9]+}/price")
    public ResponseEntity<GameDTO> updatePrice(@PathVariable long id, @RequestBody @Valid GamePriceForm form){
        Game game = gameService.updatePrice( id, form.getPrice() );
        GameDTO body = GameDTO.toDTO( game );
        return ResponseEntity.ok( body );
    }

    @PatchMapping("/{id:[0-9]+}/platforms")
    public ResponseEntity<GameDTO> addPlatform(@PathVariable long id, @RequestBody GamePlatformsForm form){
        Game game = gameService.addPlatform( id, form.getPlatforms() );
        GameDTO body = GameDTO.toDTO( game );
        return ResponseEntity.ok( body );
    }

}
