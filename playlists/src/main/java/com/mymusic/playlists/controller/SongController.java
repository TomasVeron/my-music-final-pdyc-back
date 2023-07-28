package com.mymusic.playlists.controller;

import com.mymusic.playlists.model.Genre;
import com.mymusic.playlists.model.Song;
import com.mymusic.playlists.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-playlists/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<?> getSongs(@RequestParam(value = "author",required = false) String author, @RequestParam(value = "genre", required = false) Genre genre){//
        try {
            List<Song> songs = songService.getSongs(author, genre);
            return ResponseEntity.ok(songs);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
