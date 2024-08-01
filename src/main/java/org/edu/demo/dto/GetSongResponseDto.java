package org.edu.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetSongResponseDto {
    private String title;
    private String releaseDate;
    private Integer duration;
    private String authorName;
    private List<String> genreNameList = new ArrayList<>();
}
