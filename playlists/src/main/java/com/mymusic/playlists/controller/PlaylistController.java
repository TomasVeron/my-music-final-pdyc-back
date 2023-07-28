package com.mymusic.playlists.controller;

import com.mymusic.playlists.dto.PlaylistDto;
import com.mymusic.playlists.model.Playlist;
import com.mymusic.playlists.service.PlaylistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-playlists/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PlaylistDto request, @RequestHeader("userId")String userId){
        try {
            Playlist newPlaylist = playlistService.create(request.getName(),userId);
            PlaylistDto dto = mapper.map(newPlaylist, PlaylistDto.class);
            dto.setMessage( "Se ha creado la playlist correctamente");
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getPlaylists(@RequestHeader("userId")String userId){//
        try {
            List<Playlist> playlists = playlistService.getPlaylists(userId);
            List<PlaylistDto> dtos = playlists
                    .stream()
                    .map(p -> mapper.map(p, PlaylistDto.class))
                    .toList();
            return ResponseEntity.ok(dtos);
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylist(@PathVariable("id") Long id){
        try {
            Playlist playlist = playlistService.getPlaylist(id);
            PlaylistDto dto = mapper.map(playlist, PlaylistDto.class);
            dto.setMessage("");
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateName(@PathVariable("id")Long id, @RequestBody Playlist playlistRequest, @RequestHeader("userId")String userId){//
        try {
            Playlist playlist = playlistService.update(id, playlistRequest.getName(),userId);
            PlaylistDto dto = mapper.map(playlist, PlaylistDto.class);
            dto.setMessage("Se ha actualizado el nombre correctamente");
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable("id")Long id, @RequestHeader("userId")String userId){
        try {

            Playlist playlist = playlistService.delete(id,userId);
            PlaylistDto dto = mapper.map(playlist, PlaylistDto.class);
            dto.setMessage("La playlist fue eliminada correctamente");
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/{idPlaylist}/songs/{idSong}")
    public ResponseEntity<?> addSong(@PathVariable("idPlaylist") Long idPlaylist,@PathVariable("idSong") Long idSong, @RequestHeader("userId")String userId){//
        try {
            return ResponseEntity.ok(playlistService.addSong(idPlaylist, idSong, userId));
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idPlaylist}/songs/{idSong}")
    public ResponseEntity<?> deleteSong(@PathVariable("idPlaylist") Long idPlaylist,@PathVariable("idSong") Long idSong, @RequestHeader("userId")String userId){//
        try {
            return ResponseEntity.ok(playlistService.deleteSong(idPlaylist, idSong, userId));
        }catch (Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
