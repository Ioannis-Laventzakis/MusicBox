package org.trucker.musicbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.trucker.musicbox")
public class MusicboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicboxApplication.class, args);
    }

}
