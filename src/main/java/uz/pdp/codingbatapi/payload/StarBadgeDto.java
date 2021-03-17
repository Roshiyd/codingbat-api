package uz.pdp.codingbatapi.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StarBadgeDto {
    @NotNull(message = "score shouldn't be empty!")
    private Integer score;

    private Integer progLangId;
}
