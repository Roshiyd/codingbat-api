package uz.pdp.codingbatapi.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "email field shouldn't be empty!")
    private String email;

    @NotNull(message = "email field shouldn't be empty!")
    private String password;

    private Integer starBadgeId;
}
