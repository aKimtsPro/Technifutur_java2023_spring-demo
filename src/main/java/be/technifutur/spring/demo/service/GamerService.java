package be.technifutur.spring.demo.service;

import be.technifutur.spring.demo.models.entity.Gamer;

import java.util.List;

public interface GamerService extends CrudService<Gamer, Long> {

    void addGame( Long gamerId, Long gameId );

}
