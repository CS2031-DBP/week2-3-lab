package org.edu.demo.controller;

import jakarta.validation.Valid;
import org.edu.demo.domain.Artist;
import org.edu.demo.domain.Genre;
import org.edu.demo.domain.Song;
import org.edu.demo.dto.GetSongResponseDto;
import org.edu.demo.dto.NewSongRequestDto;
import org.edu.demo.exceptions.ResourceNotFoundException;
import org.edu.demo.repository.ArtistRepository;
import org.edu.demo.repository.GenreRepository;
import org.edu.demo.repository.SongRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable("id") Long songId) {
        Song song = songRepository
                .findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song with id " + songId + " not found"));

        GetSongResponseDto songResponse = new GetSongResponseDto();
        modelMapper.map(song, songResponse);

        songResponse.setAuthorName(song.getArtist().getName());
        song.getGenres().forEach(genre -> songResponse.getGenreNameList().add(genre.getName()));

        return ResponseEntity.ok(songResponse);
    }

    @PostMapping
    public ResponseEntity<String> createSong(@Valid @RequestBody NewSongRequestDto song) {
        Song newSong = new Song();
        modelMapper.map(song, newSong);

        Artist artist = artistRepository
                .findById(song.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Artist with id " + song.getAuthorId() + " not found"));

        newSong.setArtist(artist);

        song.getGenreNameList()
                .forEach(name -> genreRepository
                        .findByName(name)
                        .ifPresent(newSong.getGenres()::add) // TODO: Handle non existing genres
                );

        Song savedSong = songRepository.save(newSong);

        URI location = URI.create("/song/" + savedSong.getId());
        return ResponseEntity.created(location).body("Song created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<Page<GetSongResponseDto>> getAllSongs(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Song> songs = songRepository.findAll(pageable);

        Page<GetSongResponseDto> songResponse = songs.map(song -> {
            GetSongResponseDto response = new GetSongResponseDto();
            modelMapper.map(song, response);

            response.setAuthorName(song.getArtist().getName());
            song.getGenres().forEach(genre -> response.getGenreNameList().add(genre.getName()));

            return response;
        });

        return ResponseEntity.ok(songResponse);
    }
}
