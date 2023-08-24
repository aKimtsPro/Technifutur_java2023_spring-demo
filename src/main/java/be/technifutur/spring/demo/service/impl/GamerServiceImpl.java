package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.models.entity.Gamer;
import be.technifutur.spring.demo.repository.GamerRepository;
import be.technifutur.spring.demo.service.GamerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamerServiceImpl implements GamerService {

    private final GamerRepository gamerRepository;

    public GamerServiceImpl(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    @Override
    public Long add(Gamer gamer) {
        gamer.setId(null);
        gamer.setPassword( generateRandomPwd() );
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
        entity.setBirthdate( gamer.getBirthdate() );

        gamerRepository.save( entity );
    }

    @Override
    public void delete(Long id) {
        Gamer gamer = getOne( id );
        gamer.setActive( false );
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
