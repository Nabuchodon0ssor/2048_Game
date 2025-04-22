package util;

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    private static final Map<String, AudioClip> cache = new HashMap<>();
    private static boolean soundEnabled = true;

    public static void play(String soundName) {
        if (!soundEnabled) return;

        try {
            AudioClip clip = cache.computeIfAbsent(soundName, name -> {
                URL resource = SoundPlayer.class.getResource("/sounds/" + name);
                if (resource != null) {
                    return new AudioClip(resource.toString());
                } else {
                    System.err.println("Sound not found: " + soundName);
                    return null;
                }
            });

            if (clip != null) {
                clip.play();
            }

        } catch (Exception e) {
            System.err.println("Failed to play sound: " + e.getMessage());
        }
    }

    public static void setSoundEnabled(boolean enabled) {
        soundEnabled = enabled;
    }

    public static boolean isSoundEnabled() {
        return soundEnabled;
    }
}