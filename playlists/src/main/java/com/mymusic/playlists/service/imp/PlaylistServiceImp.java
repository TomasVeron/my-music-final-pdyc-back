package com.mymusic.playlists.service.imp;

import com.mymusic.playlists.model.Playlist;
import com.mymusic.playlists.model.Song;
import com.mymusic.playlists.repository.PlaylistRepository;
import com.mymusic.playlists.repository.SongRepository;
import com.mymusic.playlists.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PlaylistServiceImp implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepository;



    @Override
    @Transactional
    public Playlist create(String name, String userId) throws Exception {
        try {
            if (name.equals("")){
                throw new Exception("La playlist debe tener un nombre valido");
            }
            Playlist playlist = new Playlist();
            playlist.setUidFirebase(userId);
            playlist.setName(name);
            return playlistRepository.save(playlist);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Playlist getPlaylist(Long id) throws Exception {
        Playlist playlist = playlistRepository.findById(id).orElse(null);
        if (playlist == null){
            throw new Exception("La playlist no fue encontrada");
        }
        return playlist;
    }

    @Override
    public List<Playlist> getPlaylists() throws Exception {
        return playlistRepository.findAll();
    }

    @Override
    public List<Playlist> getPlaylists(String userId) throws Exception {
        return playlistRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Playlist addSong(Long idPlaylist, Long idSong, String userId) throws Exception {
        try {
            Playlist playlistDb = playlistRepository.findByIdAndUserId(idPlaylist, userId).orElse(null);
            if (playlistDb==null){
                throw new Exception("La playlist no existe");
            }
            Song songDb = songRepository.findById(idSong).orElse(null);

            if (songDb == null){
                throw new Exception("La cancion no existe");
            }

            for (Song song : playlistDb.getSongs()) {
                if (Objects.equals(song.getId(), songDb.getId())){
                    throw new Exception("The song already exists in the playlist");
                }
            }
            playlistDb.getSongs().add(songDb);
            return playlistRepository.save(playlistDb);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Playlist update(Long id, String name, String userId) throws Exception {
        try {
            Playlist playlist = playlistRepository.findByIdAndUserId(id, userId).orElse(null);

            if (playlist==null){
                throw new Exception("La playlist no fue encontrada");
            }

            playlist.setName(name);

            return playlistRepository.save(playlist);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Playlist deleteSong(Long idPlaylist, Long idSong, String userId) throws Exception {
        try {
            Playlist playlistDb = playlistRepository.findByIdAndUserId(idPlaylist, userId).orElse(null);
            if (playlistDb==null){
                throw new Exception("La playlist no existe");
            }
            Song songDb = songRepository.findById(idSong).orElse(null);

            if (songDb == null){
                throw new Exception("La cancion no existe");
            }
            playlistDb.getSongs().remove(songDb);
            return playlistRepository.save(playlistDb);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Playlist delete(Long id, String userId) throws Exception {
        try {
            Playlist playlist = playlistRepository.findByIdAndUserId(id, userId).orElse(null);

            if (playlist==null){
                throw new Exception("La playlist no fue encontrada");
            }

            if (playlist.getSongs().size() > 0){
                playlist.getSongs().clear();
            }
            playlistRepository.delete(playlist);
            return playlist;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
