package org.trucker.musicbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trucker.musicbox.model.Artist;

import java.util.List;

/**
 * ArtistRepository interface for handling CRUD operations on Artist entities.
 * Extends JpaRepository to leverage Spring Data JPA functionalities.
 * This interface provides an abstraction layer for database operations related to Artist entities,
 * making it easier to perform common operations without writing boilerplate code.
 */
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    /**
     * Finds artists by their name.
     * This method uses Spring Data JPA's method query derivation mechanism to generate the necessary query
     * based on the method name. It automatically searches for artists where the name exactly matches the provided argument.
     *
     * @param name The name of the artist to search for.
     * @return A list of Artist entities with the specified name. If no artists are found, returns an empty list.
     */
    List<Artist> findByName(String name);

    /**
     * Custom query to find artists by part of their name.
     * This method uses the @Query annotation to define a JPQL query that searches for artists based on a substring of their name.
     * It demonstrates the flexibility of Spring Data JPA in supporting custom queries beyond simple query derivation.
     *
     * @param name The substring of the artist's name to search for.
     * @return A list of Artist entities whose names contain the specified substring. If no artists are found, returns an empty list.
     */
    @Query("SELECT a FROM Artist a WHERE a.name LIKE %:name%")
    List<Artist> findArtistsByNameContaining(@Param("name") String name);
}