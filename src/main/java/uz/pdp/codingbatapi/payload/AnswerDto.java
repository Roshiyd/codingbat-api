package uz.pdp.codingbatapi.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {
    @NotNull(message = "body field shouldn't be empty!")
    private String body;

    @NotNull(message = "taskId shouldn't be empty!")
    private Integer taskId;

    @NotNull(message = "userId shouldn't be empty!")
    private Integer userId;

    private boolean isTrue;
}
