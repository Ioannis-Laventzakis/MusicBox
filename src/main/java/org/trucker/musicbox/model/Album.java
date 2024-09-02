package org.trucker.musicbox.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Represents an album in the music box application.
 * An album is a collection of songs grouped together, usually by a single artist, and is a fundamental concept in music distribution.
 * This class models the album entity within the application, capturing essential details such as the album's title, release year, associated artist, and the songs it contains.
 */
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the album, automatically generated.

    @Column(nullable = false)
    private String title; // The title of the album. It is marked as non-nullable, meaning every album must have a title.

    private int releaseYear; // The year the album was released. This is not marked as non-nullable, so it can be left unspecified.

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist; // The artist associated with the album. This is a many-to-one relationship indicating that multiple albums can be associated with a single artist.

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Song> songs; // The set of songs in the album. This is a one-to-many relationship, indicating an album can contain multiple songs.

    // Default constructor
    public Album() {
        // Used by JPA to create instances of the class.
    }

    // Parameterized constructor
    public Album(String title, int releaseYear, Artist artist) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    // Getters and setters

    /**
     * Gets the ID of the album.
     * @return The ID of the album.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the album.
     * @param id The new ID of the album.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the album.
     * @return The title of the album.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the album.
     * @param title The new title of the album.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the release year of the album.
     * @return The release year of the album.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year of the album.
     * @param releaseYear The new release year of the album.
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the artist of the album.
     * @return The artist of the album.
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the album.
     * @param artist The new artist of the album.
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Gets the songs of the album.
     * @return The songs of the album.
     */
    public Set<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the songs of the album.
     * @param songs The new songs of the album.
     */
    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}