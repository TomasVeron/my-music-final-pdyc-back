package com.mymusic.playlists.repository;

import com.mymusic.playlists.model.Genre;
import com.mymusic.playlists.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByAuthorAndGenre(String author, Genre genre);
    List<Song> findAllByAuthor(String author);
    List<Song> findAllByGenre(Genre genre);
}
