package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Genre;
import be.technifutur.spring.demo.models.entity.Platform;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class GameDTO {

    private Long id;
    private String name;
    private List<Genre> genres;
    private LocalDate releaseDate;
    private String studioName;
    private double price;
    private List<Platform> platforms;

    public static GameDTO toDTO(Game entity){
        if( entity == null )
            return null;

        return GameDTO.builder()
                .id( entity.getId() )
                .name( entity.getName() )
                .genres( entity.getGenres() )
                .releaseDate( entity.getReleaseDate() )
                .platforms( entity.getPlatforms() )
                .price( entity.getPrice() )
                .studioName( entity.getStudio().getName() )
                .build();
    }

}
