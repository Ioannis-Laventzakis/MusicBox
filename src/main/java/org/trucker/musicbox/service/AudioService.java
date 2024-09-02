package org.trucker.musicbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.trucker.musicbox.model.Album;
import org.trucker.musicbox.model.Artist;
import org.trucker.musicbox.model.Audio;
import org.trucker.musicbox.repository.AlbumRepository;
import org.trucker.musicbox.repository.ArtistRepository;
import org.trucker.musicbox.repository.AudioRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service // This annotation indicates that this class is a Spring service.
public class AudioService {

    @Autowired // This annotation is used for automatic dependency injection.
    private AudioRepository audioRepository; // Repository for Audio entities.

    @Autowired
    private ArtistRepository artistRepository; // Repository for Artist entities.

    @Autowired
    private AlbumRepository albumRepository; // Repository for Album entities.

    private final String UPLOAD_DIR = "uploads/"; // Directory where uploaded files will be stored.

    public Audio saveAudio(MultipartFile file, String title, Long artistId, Long albumId, int duration) throws IOException {
        // Method to save an audio file and its metadata.

        // Save the file to the server
        String fileName = file.getOriginalFilename(); // Get the original file name.
        Path filePath = Paths.get(UPLOAD_DIR, fileName); // Create the file path.
        Files.createDirectories(filePath.getParent()); // Create directories if they don't exist.
        Files.write(filePath, file.getBytes()); // Write the file bytes to the path.

        // Fetch artist and album from the database
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found")); // Find the artist by ID, throw exception if not found.
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found")); // Find the album by ID, throw exception if not found.

        // Save metadata to the database
        Audio audio = new Audio(); // Create a new Audio object.
        audio.setTitle(title); // Set the title.
        audio.setArtist(artist); // Set the artist.
        audio.setAlbum(album); // Set the album.
        audio.setDuration(duration); // Set the duration.
        audio.setFilePath(filePath.toString()); // Set the file path.

        return audioRepository.save(audio); // Save the Audio object to the database and return it.
    }

    public Audio getAudio(Long id) {
        // Method to retrieve an audio file's metadata by its ID.
        return audioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audio not found with ID: " + id)); // Find the audio by ID, throw exception if not found.
    }

    public byte[] getAudioFile(Long id) throws IOException {
        // Method to retrieve the audio file by its ID.
        Audio audio = getAudio(id); // Get the Audio object by ID.
        Path filePath = Paths.get(audio.getFilePath()); // Get the file path from the Audio object.
        return Files.readAllBytes(filePath); // Read all bytes from the file and return.
    }
}
