package Battleship;//Entry of the program

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main extends Thread {
    // Volume control
    private static FloatControl gainControl;
    private static float volumeDb = (float) (20f * Math.log10(Setting.bgmVolume / 100f));


    public static void main(String[] args) {

        new Frame1();
        playBGM();
        
    }

    private static void playBGM(){
        try {
            String filePath = "res/BGM/MainMenuBGM.wav";
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            //Initialize gainControl
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            // Convert volume from percent (0–100) to decibels
            gainControl.setValue(volumeDb); // Adjust volume


            //Play BGM in a Loop Format
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void changeVolume(float volume){
        Setting.bgmVolume = volume;
        volumeDb = (float) (20f * Math.log10(Setting.bgmVolume / 100f));
        gainControl.setValue(volumeDb);
    }


}