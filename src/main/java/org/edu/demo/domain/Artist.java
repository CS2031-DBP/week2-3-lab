package org.edu.demo.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "artist")
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
}
