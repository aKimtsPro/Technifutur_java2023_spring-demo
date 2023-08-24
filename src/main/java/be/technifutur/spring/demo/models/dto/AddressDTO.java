package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String zipcode;
    private String country;

    public static AddressDTO toDTO(Address entity){
        if( entity == null )
            return null;

        return AddressDTO.builder()
                .id( entity.getId() )
                .street(entity.getStreet())
                .number(entity.getNumber())
                .city(entity.getCity())
                .zipcode(entity.getZipcode())
                .country(entity.getCountry())
                .build();
    }
}
