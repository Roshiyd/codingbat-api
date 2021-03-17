package uz.pdp.codingbatapi.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {
    @NotNull(message = "Name shouldn't be empty!")
    private String name;

    private String description;

    @NotNull(message = "progLangId shouldn't be empty!")
    private Integer progLangId;
}
