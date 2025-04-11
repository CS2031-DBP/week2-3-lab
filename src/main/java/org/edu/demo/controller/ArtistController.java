package org.edu.demo.controller;


import jakarta.validation.Valid;
import org.edu.demo.domain.Artist;
import org.edu.demo.dto.GetArtistResponseDto;
import org.edu.demo.dto.NewArtistRequestDto;
import org.edu.demo.exceptions.ResourceNotFoundException;
import org.edu.demo.repository.ArtistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<String> createArtist(@Valid @RequestBody NewArtistRequestDto artist) {
        Artist newArtist = new Artist();
        modelMapper.map(artist, newArtist);
        Artist savedArtist = artistRepository.save(newArtist);
        URI location = URI.create("/artist/" + savedArtist.getId());
        return ResponseEntity.created(location).body("Artist created successfully");
    }

    @GetMapping("{id}")
    public ResponseEntity<GetArtistResponseDto> getArtistById(@PathVariable("id") Long artistId) {
        Artist artist = artistRepository
                .findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist with id " + artistId + " not found"));
        GetArtistResponseDto artistResponse = new GetArtistResponseDto();
        modelMapper.map(artist, artistResponse);
        artist.getSongs().forEach(song -> artistResponse.getSongIdList().add(song.getId()));
        return ResponseEntity.ok(artistResponse);
    }
}
