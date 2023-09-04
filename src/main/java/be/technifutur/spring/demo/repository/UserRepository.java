package be.technifutur.spring.demo.repository;

import be.technifutur.spring.demo.models.entity.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByUsername(String username);

}
