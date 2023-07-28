package com.mymusic.playlists.repository;

import com.mymusic.playlists.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {




    @Query(value = "SELECT * FROM playlists p where  p.uid_firebase = ?1", nativeQuery = true)
    List<Playlist> findAllByUserId(String firebase_id);

    @Query(value = "SELECT * FROM playlists p where p.playlist_id = ?1 and p.uid_firebase  = ?2", nativeQuery = true)
    Optional<Playlist> findByIdAndUserId(Long idPlaylist, String userId);
}
