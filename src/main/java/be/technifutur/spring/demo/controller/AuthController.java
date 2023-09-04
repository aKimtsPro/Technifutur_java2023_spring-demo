package be.technifutur.spring.demo.controller;

import be.technifutur.spring.demo.models.dto.AuthDTO;
import be.technifutur.spring.demo.models.entity.User;
import be.technifutur.spring.demo.models.form.LoginForm;
import be.technifutur.spring.demo.models.form.RegisterForm;
import be.technifutur.spring.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public AuthController(
            UserService userService,
            UserDetailsService userDetailsService
    ) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    // POST - /auth/register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterForm form){
        User user = form.toEntity();
        userService.register( user );
        return ResponseEntity.status( HttpStatus.CREATED )
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody @Valid LoginForm form){
        String token = userService.login( form.getUsername(), form.getPassword() );
        User user = (User)userDetailsService.loadUserByUsername( form.getUsername() );
        return ResponseEntity.ok( AuthDTO.toDTO(token, user) );
    }

}
