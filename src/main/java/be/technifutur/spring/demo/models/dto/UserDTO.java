package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private Set<String> roles;

    public static UserDTO toDTO(User entity){
        if( entity == null )
            return null;

        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .roles(
                        entity.getAuthorities().stream()
                                .filter( authority -> authority.getAuthority().startsWith("ROLE_") )
                                .map( authority -> authority.getAuthority().replace("ROLE_", "") )
                                .collect(Collectors.toSet())
                )
                .build();
    }


}
