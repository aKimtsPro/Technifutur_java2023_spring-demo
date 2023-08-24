package be.technifutur.spring.demo.models.form;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class GamePriceForm {
    @PositiveOrZero
    private double price;
}
