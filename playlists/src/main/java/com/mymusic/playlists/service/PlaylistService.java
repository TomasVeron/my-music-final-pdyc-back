package com.mymusic.playlists.service;

import com.mymusic.playlists.model.Playlist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaylistService {


    Playlist create(String name, String userId) throws Exception;
    Playlist getPlaylist(Long id) throws Exception;
    List<Playlist> getPlaylists() throws Exception;
    List<Playlist> getPlaylists(String userId) throws Exception;

    Playlist addSong(Long idPlaylist, Long idSong, String userId) throws Exception;

    Playlist update(Long id, String name, String userId) throws Exception;

    Playlist deleteSong(Long idPlaylist, Long idSong, String userId) throws Exception;

    Playlist delete(Long id, String userId) throws Exception;
}
