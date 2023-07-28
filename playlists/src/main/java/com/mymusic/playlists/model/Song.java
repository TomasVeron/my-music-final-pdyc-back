package com.mymusic.playlists.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "songs")
@Data
public class Song {

    @Id
    @Column(name="song_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private Genre genre;

    private String link;
}
