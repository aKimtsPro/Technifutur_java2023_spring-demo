package be.technifutur.spring.demo.service;

import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Platform;

import java.util.List;
import java.util.Set;

public interface GameService {

    long addGame(Game game);
    Game removeGame(long id);
    Game getGame(long id);
    List<Game> getAllGames(Double minPrice, Double maxPrice);
    Game updateGame(long id, Game game);
    Game updatePrice(long id, double price);
    Game addPlatform(long id, Set<Platform> platforms);

}
