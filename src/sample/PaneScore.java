package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PaneScore {
    private Pane root = new Pane();
    public static double HEIGHT = Main.HEIGHT/12, WIDTH = Main.WIDTH;
    public static int score = 0, lives = 4;
    public static Text scoreTxt = new Text(WIDTH/2+WIDTH*2/20,HEIGHT/2,"Score: " + score);
    public static Text livesTxt = new Text(WIDTH/2-WIDTH*2/20,HEIGHT/2,"Lives: " + lives);
    static AnimationTimer timer;
    //
    public Parent mainContent(){
        root.setPrefSize(WIDTH,HEIGHT);
        root.getChildren().add(scoreTxt);
        root.getChildren().add(livesTxt);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!Main.pause)refresh();
            }
        };
        timer.start();
        return root;
    }
    public static void reset(){score=0;lives=4;}
    public static void refresh(){
        scoreTxt.setText("Score: " + score);
        livesTxt.setText("Lives: " + lives);
    }
}
