package com.mymusic.playlists.service.imp;

import com.mymusic.playlists.model.Genre;
import com.mymusic.playlists.model.Song;
import com.mymusic.playlists.repository.SongRepository;
import com.mymusic.playlists.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImp implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> getSongs(String author, Genre genre) throws Exception {
        if (author!=null && genre!=null){
            return songRepository.findAllByAuthorAndGenre(author,genre);
        }else if (author!=null){
            return songRepository.findAllByAuthor(author);
        }else if (genre != null){
            return songRepository.findAllByGenre(genre);
        }else {
            return songRepository.findAll();
        }
    }
}
