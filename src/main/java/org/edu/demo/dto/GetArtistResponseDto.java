package org.edu.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class GetArtistResponseDto {
    private String name;

    private String bio;

    private String birthDate;

    private List<Long> songIdList = new ArrayList<>();
}
