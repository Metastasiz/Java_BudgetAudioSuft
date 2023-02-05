package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.scene.Parent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PaneImg {
    private Pane root = new Pane();
    public static double HEIGHT = Main.HEIGHT*11/12, WIDTH = Main.WIDTH;
    //
    static boolean startGame = false;
    public final double BOUND = Main.WIDTH/4;
    private int DIFF_COIN = 3;
    public static SpriteImg player = new SpriteImg("player", WIDTH/2, HEIGHT-WIDTH/12, WIDTH/16, WIDTH/16, ResourceImg.car_0);
    public static SpriteImg bg = new SpriteImg("background", WIDTH/2, HEIGHT/2, WIDTH, HEIGHT, ResourceImg.bg_0_frame_0);
    private int counterCoin = 0, counter2 = 0, bg_counter = 0;
    static int coinSpawn = 50;
    static AnimationTimer timer;
    //
    static RotateTransition rotatePlayer = new RotateTransition(Duration.seconds(0.2),player);
    //
    public Parent mainContent(){
        root.setPrefSize(WIDTH,HEIGHT);
        root.getChildren().add(bg);
        root.getChildren().add(player);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!Main.pause)refresh();
            }
        };
        return root;
    }
    public static void reset(){
        player.setTranslateX(WIDTH/2);
        player.setTranslateY(HEIGHT-WIDTH/12);
        rotatePlayer.setToAngle(0);
        rotatePlayer.play();
    }
    private List<SpriteImg> allSprite(){
        return root.getChildren().stream().map(n->(SpriteImg)n).collect(Collectors.toList());
    }
    private void counter100(){
        counterCoin++;
        double coinW = WIDTH/160;
        double size = WIDTH/240;
        if (counterCoin > coinSpawn){
            if (Math.random() > 0.3){
                int a = (int)(Math.random()*3);
                SpriteImg s = new SpriteImg("coin", WIDTH/2-coinW+a*coinW, HEIGHT*51/80, size, size, ResourceImg.c_0);
                switch (a){
                    case 0: s.setVector(208);break;
                    case 1: s.setVector(270);break;
                    case 2: s.setVector(332);break;
                }
                root.getChildren().add(s);
            }
            counterCoin %= coinSpawn;
        }
    }
    private void counter10(){
        counter2++;
        if (counter2 > 2){
            bg_counter++;
            if (bg_counter > ResourceImg.bg_gif_0.size()-1)bg_counter=0;
            bg.setImage(ResourceImg.bg_gif_0.get(bg_counter));
            counter2 %=2;
        }
    }
    public void refresh() {
        if (!ResourceAudio.clip.isRunning() && startGame){try{ResourceAudio.shuffle();}catch(Exception r){}}
        counter100();
        counter10();
        allSprite().forEach(s -> {
            if (s.getTranslateX() > WIDTH || s.getTranslateX() < 0 || s.getTranslateY() > HEIGHT || s.getTranslateY() < 0){
                if (s.type.equalsIgnoreCase("coin")){
                    s.show=false;
                    PaneScore.lives--;
                    PaneScore.refresh();
                    if (PaneScore.lives <= 0){
                        deleteSprite();
                        timer.stop();
                        PaneScore.timer.stop();
                        Main.pause();
                        Main.sceneMenu();
                        Main.sceneDia();
                    }
                }
            }
            switch (s.type){
                case "coin":
                    s.incSize();
                    s.moveAuto();
                    if (s.getBoundsInParent().intersects(player.getBoundsInParent())){
                        PaneScore.score++;
                        s.show=false;
                    }
                    break;
                case "player":
                    if (player.left && !(player.getTranslateX() < 0+BOUND)){
                        player.setTranslateX(player.getTranslateX()-12);
                    } else if (player.right && !(player.getTranslateX()+player.getFitWidth() > WIDTH-BOUND)){
                        player.setTranslateX(player.getTranslateX()+12);
                    }
            }
        });
        root.getChildren().removeIf(s -> {
            SpriteImg n = (SpriteImg)s;
            return !n.show;
        });
    }
    public void deleteSprite(){
        root.getChildren().removeIf(s2 -> {
            SpriteImg n = (SpriteImg)s2;
            return n.type.equalsIgnoreCase("coin");
        });
    }
}
