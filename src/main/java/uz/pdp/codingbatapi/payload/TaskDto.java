package uz.pdp.codingbatapi.payload;

import lombok.Data;
import uz.pdp.codingbatapi.entity.Category;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull(message = "Name shouldn't be empty!")
    private String name;

    @NotNull(message = "text field shouldn't be empty!")
    private String text;

    private String examples;

    private String solution;

    private Integer categoryId;

    private boolean hasStar;
}
