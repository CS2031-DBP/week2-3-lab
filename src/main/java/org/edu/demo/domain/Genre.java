package org.edu.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="genre")
public class Genre {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column
    @Column(name = "genre_name", nullable = false, unique = true)
    private String name;

    @Column(name = "genre_description", length = 1000)
    private String description;

    // Relationships
}
