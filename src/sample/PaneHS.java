package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PaneHS {
    private Pane root = new Pane();
    private ScrollPane scrollPane = new ScrollPane();
    private VBox vbox = new VBox();
    public static double HEIGHT = Main.HEIGHT, WIDTH = Main.WIDTH;
    //
    private ListView<String> nameList = new ListView<>();
    private ListView<String> scoreList = new ListView<>();
    private Pane namePane = new Pane();
    private Pane scorePane = new Pane();
    private String[] name;
    private String[] score;
    static Button mainMenu = new Button();
    //

    public void INIT(){
        mainMenu.setPrefHeight(HEIGHT/10);
        mainMenu.setPrefWidth(WIDTH/10);
        mainMenu.setTranslateX(WIDTH/2-WIDTH/20);
        mainMenu.setTranslateY(0);
        mainMenu.setText("MAIN MENU");
        //
        root = new Pane();
        namePane = new Pane();
        vbox = new VBox();
        nameList = new ListView<>();
        scoreList = new ListView<>();
    }
    public Parent mainContent() {
        INIT();
        new Highscore();
        name = new String[Highscore.SCORE.size()];
        score = new String[Highscore.SCORE.size()];
        for (int i = 0; i < Highscore.SCORE.size(); i++) {
            name[i] = "SCORE: " + Highscore.SCORE.get(i).score + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tNAME: " + Highscore.SCORE.get(i).name ;
        }
        System.out.println(Highscore.SCORE.size());
        nameList.getItems().addAll(name);
        scoreList.getItems().addAll(score);
        //
        nameList.setPrefHeight(HEIGHT*11/12);
        nameList.setPrefWidth(WIDTH);
        nameList.setTranslateX(0);
        nameList.setTranslateY(HEIGHT/12);
        //
        //scoreList.setPrefHeight(HEIGHT*11/12);
        //scoreList.setPrefWidth(WIDTH/2);
        //scoreList.setTranslateX(WIDTH/2);
        //scoreList.setTranslateY(HEIGHT/12);
        //
        namePane.getChildren().add(nameList);
        //scorePane.getChildren().add(scoreList);
        //
        scrollPane.prefWidthProperty().bind(nameList.widthProperty());
        scrollPane.prefHeightProperty().bind(nameList.heightProperty());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(namePane);
        vbox.getChildren().add(mainMenu);
        vbox.getChildren().add(scrollPane);
        root.setPrefSize(WIDTH, HEIGHT);
        root.getChildren().add(vbox);
        return root;
    }
}

