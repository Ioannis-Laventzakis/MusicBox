package org.trucker.musicbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.trucker.musicbox.model.Audio;
import org.trucker.musicbox.service.AudioService;

import java.io.IOException;

/**
 * Controller for handling audio-related requests.
 * This controller provides endpoints for uploading audio files, retrieving audio metadata,
 * and fetching the audio file itself. It uses {@link AudioService} for the business logic.
 */
@RestController
@RequestMapping("/api/audio")
public class AudioController {

    @Autowired
    private AudioService audioService;

    /**
     * Endpoint for uploading an audio file along with its metadata.
     * The audio file and its metadata (title, artistId, albumId, duration) are provided as request parameters.
     *
     * @param file The audio file to upload.
     * @param title The title of the audio.
     * @param artistId The ID of the artist associated with the audio.
     * @param albumId The ID of the album associated with the audio.
     * @param duration The duration of the audio in seconds.
     * @return ResponseEntity containing the saved audio metadata.
     * @throws IOException If there is an error reading the file.
     */
    @PostMapping("/upload")
    public ResponseEntity<Audio> uploadAudio(@RequestParam("file") MultipartFile file,
                                             @RequestParam("title") String title,
                                             @RequestParam("artistId") Long artistId,
                                             @RequestParam("albumId") Long albumId,
                                             @RequestParam("duration") int duration) throws IOException {
        Audio audio = audioService.saveAudio(file, title, artistId, albumId, duration);
        return ResponseEntity.ok(audio);
    }

    /**
     * Endpoint to retrieve metadata of an audio file by its ID.
     *
     * @param id The ID of the audio file.
     * @return ResponseEntity containing the audio metadata.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Audio> getAudio(@PathVariable Long id) {
        Audio audio = audioService.getAudio(id);
        return ResponseEntity.ok(audio);
    }

    /**
     * Endpoint to download an audio file by its ID.
     * The audio file is returned as a byte array in the response body.
     *
     * @param id The ID of the audio file to download.
     * @return ResponseEntity containing the audio file as a byte array.
     * @throws IOException If there is an error reading the file.
     */
    @GetMapping(value = "/file/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getAudioFile(@PathVariable Long id) throws IOException {
        byte[] audioFile = audioService.getAudioFile(id);
        return ResponseEntity.ok(audioFile);
    }
}