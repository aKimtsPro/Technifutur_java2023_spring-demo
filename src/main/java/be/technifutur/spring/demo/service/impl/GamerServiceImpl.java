package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.ResourceAlreadyLinkedException;
import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.exceptions.UniqueViolationException;
import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Gamer;
import be.technifutur.spring.demo.repository.GamerRepository;
import be.technifutur.spring.demo.service.GameService;
import be.technifutur.spring.demo.service.GamerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class GamerServiceImpl implements GamerService {

    private final GamerRepository gamerRepository;
    private final GameService gameService;

    public GamerServiceImpl(GamerRepository gamerRepository, GameService gameService) {
        this.gamerRepository = gamerRepository;
        this.gameService = gameService;
    }

    @Override
    public Long add(Gamer gamer) {
        gamer.setId(null);

        List<String> fieldUniqueErrors = new LinkedList<>();
        if( gamerRepository.existsByPseudo( gamer.getPseudo() ) )
            fieldUniqueErrors.add("pseudo");

        if( gamerRepository.existsByEmail( gamer.getEmail() ) )
            fieldUniqueErrors.add("email");

        if( !fieldUniqueErrors.isEmpty() )
            throw new UniqueViolationException(fieldUniqueErrors);

        return gamerRepository.save( gamer ).getId();
    }

    @Override
    public List<Gamer> getAll() {
        return gamerRepository.findAll().stream()
                .filter( Gamer::isActive )
                .toList();
    }

    @Override
    public Gamer getOne(Long id) {
        return gamerRepository.findById(id)
                .filter( Gamer::isActive )
                .orElseThrow(() -> new ResourceNotFoundException(id, Gamer.class));
    }

    @Override
    public void update(Long id, Gamer gamer) {
        Gamer entity = getOne( id );

        entity.setPseudo( gamer.getPseudo() );
        entity.setEmail( gamer.getEmail() );
        entity.setPassword( gamer.getPassword() );
        entity.setBirthdate( gamer.getBirthdate() );

        gamerRepository.save( entity );
    }

    @Override
    public void delete(Long id) {
        Gamer gamer = getOne( id );
        gamer.setActive( false );
        gamerRepository.save( gamer );
    }

    @Override
    public void addGame(Long gamerId, Long gameId) {
        Gamer gamer = getOne( gamerId );
        Game game = gameService.getGame( gameId );

        boolean gameAlreadyPresent = gamer.getGamesPlayed().stream()
                .anyMatch( gamePlayed -> Objects.equals(gamePlayed.getId(), game.getId()) );

        if( gameAlreadyPresent )
            throw new ResourceAlreadyLinkedException(Gamer.class, gamerId, Game.class, gameId);

        gamer.getGamesPlayed().add( game );
        gamerRepository.save( gamer );
    }

    private String generateRandomPwd(){
        return "change_me";
//        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        SecureRandom secureRandom = new SecureRandom();
//
//        return IntStream.range(0, len)
//                .map(i -> secureRandom.nextInt(chars.length()))
//                .mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
//                .collect(Collectors.joining());
    }

}
