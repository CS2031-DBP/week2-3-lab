package org.edu.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewArtistRequestDto {

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Size(max = 1000)
    private String bio;

    @NotNull
    @Past
    private LocalDate birthDate;
}
