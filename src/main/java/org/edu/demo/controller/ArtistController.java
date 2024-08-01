package org.edu.demo.controller;


import org.edu.demo.domain.Artist;
import org.edu.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @PostMapping
    public ResponseEntity<String> createArtist(@RequestBody Artist artist){
        Artist newArtist = artistRepository.save(artist);
        URI location = URI.create("/artist/" + newArtist.getId());
        return ResponseEntity.created(location).body("Artist created successfully");
    }

    @GetMapping("{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("id") Long artistId){
        Artist artist = artistRepository.findById(artistId).orElse(null);
        return ResponseEntity.ok(artist);
    }
}
