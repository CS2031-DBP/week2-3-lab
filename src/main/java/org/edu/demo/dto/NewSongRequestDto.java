package org.edu.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class NewSongRequestDto {
    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Past
    private LocalDate releaseDate;

    @NotNull
    @Positive
    private Integer duration;

    /* Attribute names different from those defined in the entity
       to avoid relationships automatic mapping by the model mapper.*/
    @NotNull
    private Long authorId;
    @NotNull
    private List<String> genreNameList;
}
