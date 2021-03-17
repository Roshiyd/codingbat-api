package uz.pdp.codingbatapi.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class ProgLangDto {
    @NotNull(message = "Name shouldn't be empty!")
    private String name;

    private String description;
}
