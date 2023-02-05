package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

public class PaneMenu {
    private Pane root = new Pane();
    public static double HEIGHT = Main.HEIGHT, WIDTH = Main.WIDTH;
    //
    public static Button start = new Button();
    public static Button highscore = new Button();
    public static Button exit = new Button();
    //
    public void INIT(){
        start.setPrefHeight(HEIGHT/5);
        start.setPrefWidth(WIDTH/5);
        start.setTranslateX(WIDTH/2-WIDTH/10);
        start.setTranslateY(HEIGHT/2-HEIGHT/10-HEIGHT/5-HEIGHT/10);
        start.setText("NEW GAME");

        highscore.setPrefHeight(HEIGHT/5);
        highscore.setPrefWidth(WIDTH/5);
        highscore.setTranslateX(WIDTH/2-WIDTH/10);
        highscore.setTranslateY(HEIGHT/2-HEIGHT/10);
        highscore.setText("HIGHSCORE");

        exit.setPrefHeight(HEIGHT/5);
        exit.setPrefWidth(WIDTH/5);
        exit.setTranslateX(WIDTH/2-WIDTH/10);
        exit.setTranslateY(HEIGHT/2-HEIGHT/10+HEIGHT/5+HEIGHT/10);
        exit.setText("EXIT");
    }
    public Parent mainContent(){
        INIT();
        root.setPrefSize(WIDTH,HEIGHT);
        root.getChildren().add(start);
        root.getChildren().add(highscore);
        root.getChildren().add(exit);
        return root;
    }
}
