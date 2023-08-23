package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Genre;
import be.technifutur.spring.demo.models.entity.Platform;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class GameForm {

    private String name;
    private Set<Genre> genres;
    private LocalDate releaseDate;
    private Long studioId;
    private double price;
    private Set<Platform> platforms;


    public Game toEntity(){
        Game game = new Game();
        game.setName( this.name );
        game.setGenres( this.genres );
        game.setReleaseDate( this.releaseDate );
        game.setPrice( this.price );
        game.setPlatforms( this.platforms );
        return game;
    }

}
