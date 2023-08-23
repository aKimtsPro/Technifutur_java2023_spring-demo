package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Genre;
import be.technifutur.spring.demo.models.entity.Platform;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GameForm {

    private String name;
    private List<Genre> genres;
    private LocalDate releaseDate;
    private String studioName;
    private double price;
    private List<Platform> platforms;

    public Game toEntity(){
        Game game = new Game();
        game.setName( this.name );
        game.setGenres( this.genres );
        game.setReleaseDate( this.releaseDate );
//        game.setStudioName( this.studioName );
        game.setPrice( this.price );
        game.setPlatforms( this.platforms );
        return game;
    }

}
