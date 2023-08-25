package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Studio;
import be.technifutur.spring.demo.validation.contraints.IsOk;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class StudioForm {

    @NotBlank
    @IsOk
    private String name;
    @Valid
    private AddressForm address;

    public Studio toEntity(){
        Studio studio = new Studio();
        studio.setName( this.name );
        studio.setAddress( this.address.toEntity() );
        return studio;
    }

}
