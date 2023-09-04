package be.technifutur.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf( AbstractHttpConfigurer::disable )
            .httpBasic( AbstractHttpConfigurer::disable );

        // /** : 0 à N segments uri
        // /*  : 0 à 1 segment uri
        // ?   : n'importe quel caractère

        http.authorizeHttpRequests( registry -> {
            registry.requestMatchers(HttpMethod.HEAD).hasRole("ADMIN")
                    .requestMatchers("/test/**").anonymous()
                    // OK: /studio, /studio/1,
                    // PAS OK: /studio/1/delete
                    .requestMatchers(HttpMethod.POST, "/studi?/*").hasAuthority("ROLE_USER")
                    .requestMatchers( request -> request.getParameterMap().size() > 5 ).authenticated();
        } );



        return http.build();

    }

}
