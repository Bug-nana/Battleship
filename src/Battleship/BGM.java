package Battleship;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class BGM {

    // Volume control
    private static FloatControl gainControl;
    private static float volumeDb = (float) (20f * Math.log10(Setting.bgmVolume / 100f));
    private Clip currentClip;
    private AudioInputStream audioStream;

    public BGM(String filename) {
        playBGM(filename);
    }

    public static void changeVolume(float volume) {
        Setting.bgmVolume = volume;
        volumeDb = (float) (20f * Math.log10(Setting.bgmVolume / 100f));
        gainControl.setValue(volumeDb);
    }

    public void playBGM(String filename) {
        try {
            File audioFile = new File(filename);
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);

            //Initialize gainControl
            gainControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
            // Convert volume from percent (0–100) to decibels
            gainControl.setValue(volumeDb); // Adjust volume


            //Play BGM in a Loop Format
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            currentClip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeTrack(String filename) {
        try {
            // Stop the current clip if it's playing
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close(); // Close the current clip
            }

            playBGM(filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
