package com.mymusic.playlists.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "playlists")
@Data
public class Playlist {
    @Id
    @Column(name="playlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Song> songs;

    @Column(name = "uid_firebase")
    private String uidFirebase;
}
