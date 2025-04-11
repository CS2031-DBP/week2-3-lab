package org.edu.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class GetSongResponseDto {
    private String title;

    private String releaseDate;

    private Integer duration;

    private String authorName;
   
    private List<String> genreNameList = new ArrayList<>();
}
