package org.edu.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
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
