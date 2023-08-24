package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Studio;
import lombok.Data;

@Data
public class StudioForm {

    private String name;
    private AddressForm address;

    public Studio toEntity(){
        Studio studio = new Studio();
        studio.setName( this.name );
        studio.setAddress( this.address.toEntity() );
        return studio;
    }

}
