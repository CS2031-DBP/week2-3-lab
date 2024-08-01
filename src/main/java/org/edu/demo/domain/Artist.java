package org.edu.demo.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Artist {
    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Columns
    @Column(name = "artist_name", nullable = false)
    private String name;

    @Column(name = "artist_bio", length = 1000)
    private String bio;

    @Column(name = "artist_birth_date", nullable = false)
    private LocalDate birthDate;

    // Relationships
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Song> songs;

}
