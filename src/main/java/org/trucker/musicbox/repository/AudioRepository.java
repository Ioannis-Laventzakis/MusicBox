package org.trucker.musicbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trucker.musicbox.model.Audio;

public interface AudioRepository extends JpaRepository<Audio, Long> {
    // Additional query methods if needed

}
