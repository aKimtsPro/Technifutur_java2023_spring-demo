package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Platform;
import be.technifutur.spring.demo.repository.GameRepository;
import be.technifutur.spring.demo.service.GameService;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public List<Game> getAllGames(Double minPrice, Double maxPrice) {
        return null;
    }

    @Override
    public Game updateGame(long id, Game game) {
        return null;
    }

    @Override
    public Game updatePrice(long id, double price) {
        return null;
    }

    @Override
    public Game addPlatform(long id, Set<Platform> platforms) {
        return null;
    }
}
