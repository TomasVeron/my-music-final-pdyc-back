package com.mymusic.playlists.service;

import com.mymusic.playlists.model.Genre;
import com.mymusic.playlists.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {

    List<Song> getSongs(String author, Genre genre) throws Exception;
}
