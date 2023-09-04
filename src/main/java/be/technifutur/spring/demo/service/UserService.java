package be.technifutur.spring.demo.service;

import be.technifutur.spring.demo.models.entity.User;

public interface UserService {

    void register(User user);
    String login(String username, String password);

}
