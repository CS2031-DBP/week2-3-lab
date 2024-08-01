package org.edu.demo.controller;

import jakarta.validation.Valid;
import org.edu.demo.domain.Genre;
import org.edu.demo.dto.NewGenreRequestDto;
import org.edu.demo.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<String> createGenre(@Valid @RequestBody NewGenreRequestDto genre){
        Genre newGenre = new Genre();
        modelMapper.map(genre, newGenre);

        Genre savedGenre = genreRepository.save(newGenre);

        URI location = URI.create("/genres/" + savedGenre.getId());
        return ResponseEntity.created(location).body("Genre created successfully");
    }
}
