package org.trucker.musicbox.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Represents a playlist in the music box application.
 * A playlist is a curated collection of songs, typically created by a user, that can be played in a specific order or shuffled.
 * This class models the playlist entity within the application, capturing essential details such as the playlist's name, the user who created it, and the songs it contains.
 */
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the playlist, automatically generated.

    @Column(nullable = false)
    private String name; // The name of the playlist. It is marked as non-nullable, meaning every playlist must have a name.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // The user who created the playlist. This is a many-to-one relationship, indicating that a user can create multiple playlists.

    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> songs; // The set of songs included in the playlist. This is a many-to-many relationship, as a song can be included in multiple playlists and a playlist can contain multiple songs.

    // Default constructor
    public Playlist() {
        // Used by JPA to create instances of the class.
    }

    // Parameterized constructor
    public Playlist(String name, User user, Set<Song> songs) {
        this.name = name;
        this.user = user;
        this.songs = songs;
    }

    // Getters and setters

    /**
     * Gets the ID of the playlist.
     * @return The ID of the playlist.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the playlist.
     * @param id The new ID of the playlist.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the playlist.
     * @return The name of the playlist.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the playlist.
     * @param name The new name of the playlist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user associated with the playlist.
     * @return The user associated with the playlist.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the playlist.
     * @param user The new user associated with the playlist.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the songs included in the playlist.
     * @return The songs included in the playlist.
     */
    public Set<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the songs included in the playlist.
     * @param songs The new songs to be included in the playlist.
     */
    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}