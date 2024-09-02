package org.trucker.musicbox.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Represents an artist in the music box application.
 * An artist can have multiple albums associated with them, capturing the essence of their musical contributions over time.
 * This class models the artist entity within the application, detailing their name and the albums they have produced.
 */
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the artist, automatically generated.

    @Column(nullable = false)
    private String name; // The name of the artist. It is marked as non-nullable, meaning every artist must have a name.

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Album> albums; // The set of albums by the artist. This is a one-to-many relationship, indicating an artist can produce multiple albums.

    // Default constructor
    public Artist() {
        // Used by JPA to create instances of the class.
    }

    // Parameterized constructor
    public Artist(String name) {
        this.name = name;
    }

    // Getters and setters

    /**
     * Gets the ID of the artist.
     * @return The ID of the artist.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the artist.
     * @param id The new ID of the artist.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the artist.
     * @return The name of the artist.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the artist.
     * @param name The new name of the artist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the albums associated with the artist.
     * @return The albums associated with the artist.
     */
    public Set<Album> getAlbums() {
        return albums;
    }

    /**
     * Sets the albums associated with the artist.
     * @param albums The new albums to be associated with the artist.
     */
    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}