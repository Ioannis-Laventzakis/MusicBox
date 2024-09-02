package org.trucker.musicbox.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Represents a song in the music box application.
 * A song is a piece of music that has been recorded and can be played back. It is a fundamental unit of music, often featuring vocals and instrumental accompaniment.
 * This class models the song entity within the application, capturing essential details such as the song's title, duration, associated album, and the playlists it is part of.
 */
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the song, automatically generated.

    @Column(nullable = false)
    private String title; // The title of the song. It is marked as non-nullable, meaning every song must have a title.

    private int duration; // The duration of the song in seconds. This is not marked as non-nullable, so it can be left unspecified.

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album; // The album associated with the song. This is a many-to-one relationship indicating that multiple songs can be associated with a single album.

    @ManyToMany(mappedBy = "songs")
    private Set<Playlist> playlists; // The set of playlists that include the song. This is a many-to-many relationship, as a song can be included in multiple playlists and a playlist can contain multiple songs.

    // Default constructor
    public Song() {
        // Used by JPA to create instances of the class.
    }

    // Parameterized constructor
    public Song(String title, int duration, Album album) {
        this.title = title;
        this.duration = duration;
        this.album = album;
    }

    // Getters and setters

    /**
     * Gets the ID of the song.
     * @return The ID of the song.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the song.
     * @param id The new ID of the song.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the song.
     * @return The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song.
     * @param title The new title of the song.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the duration of the song.
     * @return The duration of the song.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the song.
     * @param duration The new duration of the song.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the album associated with the song.
     * @return The album associated with the song.
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * Sets the album associated with the song.
     * @param album The new album associated with the song.
     */
    public void setAlbum(Album album) {
        this.album = album;
    }

    /**
     * Gets the playlists that include the song.
     * @return The playlists that include the song.
     */
    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Sets the playlists that include the song.
     * @param playlists The new playlists that will include the song.
     */
    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }
}