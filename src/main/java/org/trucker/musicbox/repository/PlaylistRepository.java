package org.trucker.musicbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trucker.musicbox.model.Playlist;

import java.util.List;

/**
 * PlaylistRepository interface for handling CRUD operations on Playlist entities.
 * Extends JpaRepository to leverage Spring Data JPA functionalities.
 * This interface defines methods for querying the database for Playlist entities based on various criteria.
 * It utilizes Spring Data JPA to simplify the implementation of data access layers.
 */
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    /**
     * Finds playlists by their name.
     * This method leverages Spring Data JPA's method query derivation mechanism to generate the necessary query
     * based on the method name. It automatically searches for playlists where the name matches the provided argument.
     *
     * @param name The name of the playlist to search for.
     * @return A list of Playlist entities with the specified name. If no playlists are found, returns an empty list.
     */
    List<Playlist> findByName(String name);

    /**
     * Custom query to find playlists by user's id.
     * This method uses the @Query annotation to define a JPQL query that searches for playlists based on the user's id.
     * It demonstrates the flexibility of Spring Data JPA in supporting custom queries beyond simple query derivation.
     *
     * @param userId The id of the user whose playlists are to be searched.
     * @return A list of Playlist entities associated with the specified user's id. If no playlists are found, returns an empty list.
     */
    @Query("SELECT p FROM Playlist p WHERE p.user.id = :userId")
    List<Playlist> findPlaylistsByUserId(@Param("userId") Long userId);
}