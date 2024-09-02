package org.trucker.musicbox.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents a user in the music box application.
 * A user has a unique username and email, and can create multiple playlists.
 * Users can also have a premium status, granting them additional privileges.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Changed to IDENTITY for compatibility
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;  // Ensure to hash passwords before storing

    @Column(nullable = false, unique = true)
    private String email;

    private String status;

    private boolean isPremium;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Playlist> playlists;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public String getStatus() {
        return status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;  // Remember to hash the password before setting it
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setIsPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

