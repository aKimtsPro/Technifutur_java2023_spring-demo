package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.UniqueViolationException;
import be.technifutur.spring.demo.jwt.JWTUtils;
import be.technifutur.spring.demo.models.entity.User;
import be.technifutur.spring.demo.repository.UserRepository;
import be.technifutur.spring.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder encoder,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(User user) {
        Assert.notNull(user, "user should not be null");

        if( userRepository.existsByUsername(user.getUsername()) )
            throw new UniqueViolationException("username");

        user.setPassword( encoder.encode(user.getPassword()) );
        userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        Authentication auth = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username, password));
        return JWTUtils.generateJwt( auth );
    }
}
