package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double WIDTH = screenSize.getWidth();
    public static double HEIGHT = screenSize.getHeight();
    //
    public static boolean pause = false;
    //
    static PaneImg paneImg = new PaneImg();
    static PaneScore paneScore = new PaneScore();
    static PaneMenu paneMenu = new PaneMenu();
    static PaneHS paneHS = new PaneHS();
    static PaneDialog paneDialog = new PaneDialog();
    //
    static VBox menu = new VBox();
    static VBox game = new VBox();
    static VBox hs = new VBox();
    //
    static Scene sceneMenu;
    static Scene sceneGame;
    static Scene sceneHS;
    static Scene sceneDia;
    //
    static Stage stage = new Stage();
    static Stage stageDia = new Stage();
    static Controller control = new Controller();
    //static Stage primaryStage;
    //
    static void reset(){
        paneImg.reset();
        paneScore.reset();
    }
    static void pause(){
        pause = !pause;
        ResourceAudio.pause();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        //
        Highscore.load();
        menu.getChildren().add(paneMenu.mainContent());
        game.getChildren().add(paneScore.mainContent());
        game.getChildren().add(paneImg.mainContent());
        hs.getChildren().add(paneHS.mainContent());
        //
        sceneDia = new Scene(paneDialog.mainContent());
        sceneMenu = new Scene(menu);
        sceneGame = new Scene(game);
        sceneHS = new Scene(hs);
        //
        control.addScene(sceneMenu);
        control.setControl();
        stage.setScene(sceneMenu);
        stage.setFullScreen(true);
        stage.show();
        //
        stageDia.setScene(sceneDia);
        stageDia.setMaxHeight(HEIGHT/5);
        stageDia.setMaxWidth(WIDTH/5);
    }
    public static void sceneGame(){
        PaneImg.startGame = true;
        control.addScene(sceneGame);
        control.setControl();
        stage.setScene(sceneGame);
        stage.setFullScreen(true);
        stage.show();
    }
    public static void sceneMenu(){
        PaneImg.startGame = false;
        control.addScene(sceneMenu);
        control.setControl();
        stage.setScene(sceneMenu);
        stage.setFullScreen(true);
        stage.show();
    }
    public static void sceneHS(){
        PaneImg.startGame = false;
        hs = new VBox();
        hs.getChildren().add(paneHS.mainContent());
        sceneHS = new Scene(hs);
        control.addScene(sceneHS);
        control.setControl();
        stage.setScene(sceneHS);
        stage.setFullScreen(true);
        stage.show();
    }
    public static void sceneDia(){
        stageDia.setFullScreen(false);
        stageDia.show();
    }
    private static class Controller{
        Scene scene;
        KeyCombination pause = new KeyCodeCombination(KeyCode.Q,KeyCodeCombination.SHIFT_DOWN,KeyCodeCombination.CONTROL_DOWN);
        KeyCombination skipSong = new KeyCodeCombination(KeyCode.K,KeyCodeCombination.SHIFT_DOWN,KeyCodeCombination.CONTROL_DOWN);

        void addScene(Scene a){
            scene = a;
        }
        void setControl(){
            paneMenu.start.setOnAction(e ->{
                Main.pause = false;
                reset();
                sceneGame();
                PaneImg.timer.start();
                PaneScore.timer.start();
            });
            paneMenu.highscore.setOnAction(e ->{
                new Highscore();
                sceneHS();
            });
            paneHS.mainMenu.setOnAction(e ->{
                sceneMenu();
            });
            paneMenu.exit.setOnAction(e ->{
                stage.close();
            });
            scene.setOnKeyPressed(e -> {
                if (pause.match(e)){
                    pause();
                } else if (skipSong.match(e)){
                    System.out.println("shuffle");
                    try{ResourceAudio.pause();ResourceAudio.shuffle();}
                    catch(Exception r){}
                }
                if (!Main.pause) {
                    switch (e.getCode()) {
                        case A:
                            paneImg.rotatePlayer.stop();
                            paneImg.rotatePlayer.setFromAngle(paneImg.player.angle);
                            paneImg.rotatePlayer.setToAngle(-30);
                            paneImg.rotatePlayer.play();
                            paneImg.player.angle = -30;
                            if (paneImg.player.getTranslateX() < 0 + paneImg.BOUND) break;
                            //
                            paneImg.player.right = false;
                            paneImg.player.left = true;
                            break;
                        case D:
                            paneImg.rotatePlayer.stop();
                            paneImg.rotatePlayer.setFromAngle(paneImg.player.angle);
                            paneImg.rotatePlayer.setToAngle(30);
                            paneImg.rotatePlayer.play();
                            paneImg.player.angle = 30;
                            if (paneImg.player.getTranslateX() > WIDTH - paneImg.BOUND) break;
                            //
                            paneImg.player.left = false;
                            paneImg.player.right = true;
                            break;
                    }
                }
            });
            scene.setOnKeyReleased(e -> {
                if (!Main.pause) {
                    switch (e.getCode()) {
                        case A:
                            paneImg.rotatePlayer.stop();
                            paneImg.rotatePlayer.setFromAngle(paneImg.player.angle);
                            paneImg.rotatePlayer.setToAngle(0);
                            paneImg.rotatePlayer.play();
                            paneImg.player.angle = 0;
                            //
                            paneImg.player.left = false;
                            break;
                        case D:
                            paneImg.rotatePlayer.stop();
                            paneImg.rotatePlayer.setFromAngle(paneImg.player.angle);
                            paneImg.rotatePlayer.setToAngle(0);
                            paneImg.rotatePlayer.play();
                            paneImg.player.angle = 0;
                            //
                            paneImg.player.right = false;
                            break;
                    }
                }
            });
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
/*
PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) b.getX();
                int y = (int) b.getY();
                System.out.print(y + " - ");
                System.out.println(x);
 */