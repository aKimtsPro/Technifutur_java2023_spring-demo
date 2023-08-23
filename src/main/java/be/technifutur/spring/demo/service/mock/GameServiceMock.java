package be.technifutur.spring.demo.service.mock;

import be.technifutur.spring.demo.exceptions.ResourceNotFound2Exception;
import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Platform;
import be.technifutur.spring.demo.service.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Service
public class GameServiceMock implements GameService {

    private static long nextId = 1;
    private final List<Game> games = new ArrayList<>();


    @Override
    public long addGame(Game game) {
        if( game == null )
            throw new IllegalArgumentException("game can't be null");

        game.setId( nextId++ );
        games.add( game );
        return game.getId();
    }

    @Override
    public Game removeGame(long id) {
        Game game = getGame(id);
        games.remove(game);
        return game;
    }

    @Override
    public Game getGame(long id) {
        return games.stream()
                .filter( game -> game.getId() == id )
                .findAny()
                .orElseThrow( () -> new ResourceNotFound2Exception(id, Game.class));
    }

    @Override
    public List<Game> getAllGames(Double minPrice, Double maxPrice) {
        return games.stream()
                .filter( game -> minPrice == null || game.getPrice() >= minPrice )
                .filter( game -> maxPrice == null || game.getPrice() <= maxPrice )
                .toList();
    }

    @Override
    public Game updateGame(long id, Game game) {

        if( game == null )
            throw new IllegalArgumentException("game can't be null");

        Game toUpdate = getGame(id);

        toUpdate.setName(game.getName() );
        toUpdate.setGenres(game.getGenres());
        toUpdate.setPrice(game.getPrice());
        toUpdate.setPlatforms(game.getPlatforms());
        toUpdate.setStudio(game.getStudio());
        toUpdate.setReleaseDate(game.getReleaseDate());

        return toUpdate;

    }

    @Override
    public Game updatePrice(long id, double price) {
        if( price < 0 )
            throw new IllegalArgumentException("price should be 0 or positive");

        Game toUpdate = getGame(id);

        toUpdate.setPrice(price);

        return toUpdate;
    }

    @Override
    public Game addPlatform(long id, Set<Platform> platforms) {
        if( platforms == null )
            throw new IllegalArgumentException("platform should not be null");

        Game game = getGame(id);

        game.getPlatforms().addAll(
                platforms.stream()
                        .distinct()
                        .filter( platform -> !game.getPlatforms().contains(platform) )
                        .toList()
        );

        return game;
    }
}
