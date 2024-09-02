package org.trucker.musicbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trucker.musicbox.model.Song;

import java.util.List;

/**
 * SongRepository interface for handling CRUD operations on Song entities.
 * Extends JpaRepository to leverage Spring Data JPA functionalities.
 */
public interface SongRepository extends JpaRepository<Song, Long> {

    /**
     * Finds songs by their title.
     * This method leverages Spring Data JPA's method query derivation mechanism to generate the necessary query
     * based on the method name. It automatically searches for songs where the title matches the provided argument.
     *
     * @param title The title of the song to search for.
     * @return A list of Song entities with the specified title. If no songs are found, returns an empty list.
     */
    List<Song> findByTitle(String title);

    /**
     * Custom query to find songs by album's id.
     * This method uses the @Query annotation to define a JPQL query that searches for songs based on the album's id.
     * It demonstrates the flexibility of Spring Data JPA in supporting custom queries beyond simple query derivation.
     *
     * @param albumId The id of the album whose songs are to be searched.
     * @return A list of Song entities associated with the specified album's id. If no songs are found, returns an empty list.
     */
    @Query("SELECT s FROM Song s WHERE s.album.id = :albumId")
    List<Song> findSongsByAlbumId(@Param("albumId") Long albumId);
}