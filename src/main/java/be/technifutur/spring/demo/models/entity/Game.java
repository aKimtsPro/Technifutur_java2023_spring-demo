package be.technifutur.spring.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    private Long id;
    private String name;
    private List<Genre> genres = new ArrayList<>();
    private LocalDate releaseDate;
    private String studioName;
    private double price;
    private List<Platform> platforms = new ArrayList<>();

    public void setGenres(List<Genre> genres) {
        this.genres = new ArrayList<>(genres);
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = new ArrayList<>(platforms);
    }
}
