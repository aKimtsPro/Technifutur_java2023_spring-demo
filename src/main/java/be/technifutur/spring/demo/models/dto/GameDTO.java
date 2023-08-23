package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Competition;
import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Genre;
import be.technifutur.spring.demo.models.entity.Platform;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class GameDTO {

    private Long id;
    private String name;
    private Set<Genre> genres;
    private LocalDate releaseDate;
    private String studioName;
    private double price;
    private Set<Platform> platforms;
    private Set<SmallCompetitionDTO> competitions;

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
                .competitions(
                        entity.getCompetitions().stream()
                                .map(SmallCompetitionDTO::toDTO)
                                .collect(Collectors.toSet())
                )
                .build();
    }

}
