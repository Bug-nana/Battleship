package Battleship;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private Clip clip;

    public void hit() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("res/SoundEffect/hit.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
    }

    public void miss() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("res/SoundEffect/miss.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
    }


    public void play() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop(); // Stop previous play
            }
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
        }
    }

}