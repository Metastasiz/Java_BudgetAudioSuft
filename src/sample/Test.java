package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Test extends Application {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double WIDTH = screenSize.getWidth();
    private double HEIGHT = screenSize.getHeight();
    private Pane root = new Pane();

    private Parent mainContent(){
        root.setPrefSize(WIDTH,HEIGHT);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
            }
        };
        timer.start();
        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        VBox box = new VBox();


        Scene scene = new Scene(mainContent());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    public static void main(String[] arg){

    }
}
