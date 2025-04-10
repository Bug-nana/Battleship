package Battleship;//Entry of the program

import javax.sound.sampled.*;
import java.io.File;

public class Main extends Thread {

    public static BGM bgm = new BGM("res/BGM/MainMenuBGM.wav");

    public static void main(String[] args) {


        new Frame1();
        
    }

}