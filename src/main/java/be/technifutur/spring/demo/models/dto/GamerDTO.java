package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Gamer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class GamerDTO {
    private Long id;
    private String pseudo;
    private String email;
    private LocalDate birthdate;
    private Set<SmallGameDTO> games;
    private Set<SmallParticipationDTO> participations;

    public static GamerDTO toDTO(Gamer entity){
        if( entity == null )
            return null;

        return GamerDTO.builder()
                .id( entity.getId() )
                .pseudo( entity.getPseudo() )
                .email(  entity.getEmail() )
                .birthdate( entity.getBirthdate() )
                .games(
                        entity.getGamesPlayed().stream()
                            .map( SmallGameDTO::toDTO )
                            .collect(Collectors.toSet())
                )
                .participations(
                        entity.getParticipations().stream()
                                .map( SmallParticipationDTO::toDTO )
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
