package sample;

import javafx.scene.image.Image;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ResourceAudio {
    static Clip clip;
    static Clip ding;
    static boolean ifDing = true;
    static int cur = -1;

    static {
        try {
            clip = AudioSystem.getClip();
            ding = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) throws Exception {
        Scanner scan = new Scanner(System.in);
        //play(1);
        //Thread.sleep(1000);
        //ding();
        int a = scan.nextInt();
    }
    public static File
            audio_ding = new File("resources/audio/ding.wav"),
            audio_0 = new File("resources/audio/a0.wav"),
            audio_1 = new File("resources/audio/a1.wav"),
            audio_2 = new File("resources/audio/a2.wav");
    public static Map<Integer, File> audioMap = new HashMap<>(){
        {
            put(0   ,audio_0 );
            put(1   ,audio_1 );
            put(2   ,audio_2 );
        }
    };
    public static void shuffle() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip.close();
        int i = 0;
        do{i = (int)(Math.random()*audioMap.size());}
        while (i == cur);
        AudioInputStream a = AudioSystem.getAudioInputStream(audioMap.get(i));
        clip.open(a);
        clip.start();
        cur = i;
    }
    public static void play(int i) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip.close();
        AudioInputStream a = AudioSystem.getAudioInputStream(audioMap.get(i));
        clip.open(a);
        clip.start();
        cur = i;
    }
    public static void pause(){
        if (clip.isRunning()) clip.stop();
        else {clip.start();}
    }
    public static void reset(){
        clip.setMicrosecondPosition(0);
    }
    public static void ding() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        if (ifDing){
            AudioInputStream a = AudioSystem.getAudioInputStream(audio_ding);
            clip.open(a);
            clip.start();
        }
    }
}
