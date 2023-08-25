package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Gamer;
import be.technifutur.spring.demo.validation.contraints.EmailStartWithPseudo;
import be.technifutur.spring.demo.validation.contraints.EmailUnique;
import be.technifutur.spring.demo.validation.contraints.TimesAgo;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@EmailStartWithPseudo
public class GamerForm {

    @NotBlank
    @Size(min = 6, max = 20)
    private String pseudo;
    @NotBlank
    @Email
    @EmailUnique
    private String email;
    @NotBlank
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[!=@#|$%^&*()_+{}\\\\[\\\\]:;<>,.?~\\\\-]).*(?=.*[A-Z]).*(?=.*[0-9]).*$")
    private String password;
    @NotNull
    @TimesAgo(/*years = 12,*/ message = "should be at least 12 years old")
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
