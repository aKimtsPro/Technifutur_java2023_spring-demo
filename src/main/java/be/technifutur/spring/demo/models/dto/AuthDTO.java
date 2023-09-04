package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDTO {

    private String token;
    private UserDTO user;

    public static AuthDTO toDTO(String token, User user){
        return AuthDTO.builder()
                .token( token )
                .user( UserDTO.toDTO(user) )
                .build();
    }


}
