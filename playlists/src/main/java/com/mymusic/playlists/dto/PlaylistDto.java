package com.mymusic.playlists.dto;

import com.mymusic.playlists.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistDto {

    private Long id;

    private String name;

    private UserDto user;

    private List<Song> songs;

    private String message;

    public PlaylistDto(Long id,  String name, UserDto user, List<Song> songs,String message) {
        setId(id);
        setName(name);
        setUser(user);
        setSongs(songs);
        setMessage(message);
    }

    public PlaylistDto(){}
}
