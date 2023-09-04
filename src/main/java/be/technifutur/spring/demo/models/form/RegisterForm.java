package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterForm {

    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[!=@#|$%^&*()_+{}\\\\[\\\\]:;<>,.?~\\\\-]).*(?=.*[A-Z]).*(?=.*[0-9]).*$")
    private String password;

    public User toEntity(){
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        return entity;
    }

}
