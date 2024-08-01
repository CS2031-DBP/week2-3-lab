package org.edu.demo.controller;

import org.edu.demo.domain.Song;
import org.edu.demo.exceptions.ErrorMessage;
import org.edu.demo.exceptions.ResourceNotFoundException;
import org.edu.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable("id") Long songId) {
        Song song = songRepository
                .findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song with id " + songId + " not found"));

        return ResponseEntity.ok(song);
    }

    @PostMapping
    public ResponseEntity<String> createSong(@RequestBody Song song) {
        Song newSong = songRepository.save(song);
        URI location = URI.create("/song/" + newSong.getId());
        return ResponseEntity.created(location).body("Song created successfully");
    }
}
