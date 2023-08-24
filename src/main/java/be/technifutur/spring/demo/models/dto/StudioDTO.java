package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Studio;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudioDTO {

    private Long id;
    private String name;
    private AddressDTO address;
    private List<SmallGameDTO> games;

    public static StudioDTO toDTO(Studio entity){
        if (entity == null)
            return null;

        return StudioDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address( AddressDTO.toDTO(entity.getAddress()) )
                .games(
                        entity.getGames().stream()
                            .map(SmallGameDTO::toDTO)
                            .toList()
                )
                .build();
    }

}
