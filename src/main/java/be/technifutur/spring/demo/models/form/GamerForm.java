package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Gamer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GamerForm {

    private String pseudo;
    private String email;
    private String password;
    private LocalDate birthdate;

    public Gamer toEntity(){
        Gamer gamer = new Gamer();
        gamer.setPseudo( pseudo );
        gamer.setEmail( email );
        gamer.setPassword( password );
        gamer.setBirthdate( birthdate );
        return gamer;
    }

}
