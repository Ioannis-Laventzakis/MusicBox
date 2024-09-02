package org.trucker.musicbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trucker.musicbox.model.Album;

import java.util.List;

/**
 * AlbumRepository interface for handling CRUD operations on Album entities.
 * Extends JpaRepository to leverage Spring Data JPA functionalities.
 * This interface defines methods for querying the database for Album entities based on various criteria.
 * It utilizes Spring Data JPA to simplify the implementation of data access layers.
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {

    /**
     * Finds albums by their title.
     * This method leverages Spring Data JPA's method query derivation mechanism to generate the necessary query
     * based on the method name. It automatically searches for albums where the title matches the provided argument.
     *
     * @param title The title of the album to search for.
     * @return A list of Album entities with the specified title. If no albums are found, returns an empty list.
     */
    List<Album> findByTitle(String title);

    /**
     * Finds albums by their release year.
     * Similar to findByTitle, this method uses query derivation to find albums where the release year matches
     * the provided argument. It simplifies the process of querying albums based on their release year.
     *
     * @param releaseYear The release year of the albums to search for.
     * @return A list of Album entities released in the specified year. If no albums are found, returns an empty list.
     */
    List<Album> findByReleaseYear(int releaseYear);

    /**
     * Custom query to find albums by the artist's name.
     * This method uses the @Query annotation to define a JPQL query that searches for albums based on the artist's name.
     * It demonstrates the flexibility of Spring Data JPA in supporting custom queries beyond simple query derivation.
     *
     * @param artistName The name of the artist whose albums are to be searched.
     * @return A list of Album entities associated with the specified artist's name. If no albums are found, returns an empty list.
     */
    @Query("SELECT a FROM Album a WHERE a.artist.name = :artistName")
    List<Album> findAlbumsByArtistName(@Param("artistName") String artistName);
}