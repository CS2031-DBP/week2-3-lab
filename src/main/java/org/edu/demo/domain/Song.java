package org.edu.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="song")
public class Song {
    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Columns
    @Column(name = "song_title", nullable = false)
    private String title;

    @Column(name = "song_lyrics", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "song_duration", nullable = false, columnDefinition = "INT UNSIGNED")
    private Integer duration;

    // Relationships
}
