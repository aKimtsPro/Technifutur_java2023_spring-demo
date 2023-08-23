package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Platform;
import be.technifutur.spring.demo.repository.GameRepository;
import be.technifutur.spring.demo.service.GameService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public long addGame(Game game) {
        game = gameRepository.save( game );
        return game.getId();
    }

    @Override
    public Game removeGame(long id) {
        Game game = getGame( id );
        gameRepository.delete( game );
        return game;
    }

    @Override
    public Game getGame(long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Game.class));
    }

    @Override
    public List<Game> getAllGames(Double minPrice, Double maxPrice) {
        return gameRepository.findAll().stream()
                .filter(game -> minPrice == null || game.getPrice() >= minPrice)
                .filter(game -> maxPrice == null || game.getPrice() <= maxPrice)
                .toList();
    }

    @Override
    public Game updateGame(long id, Game game) {
        game.setId(id);
        return gameRepository.save( game );
    }

    @Override
    public Game updatePrice(long id, double price) {
        if( price < 0 )
            throw new IllegalArgumentException("price should be positive or 0");

        Game game = getGame(id);
        game.setPrice( price );
        return gameRepository.save( game );
    }

    @Override
    public Game addPlatform(long id, Set<Platform> platforms) {
        Assert.notNull(platforms, "platforms shoud not be null");

        Game game = getGame(id);
        game.getPlatforms().addAll(platforms); // TODO pas ajouter une plateforme existante
        return gameRepository.save( game );
    }
}
