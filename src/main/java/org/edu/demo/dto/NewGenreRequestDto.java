package org.edu.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class NewGenreRequestDto {
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Size(max = 1000)
    private String description;
}
