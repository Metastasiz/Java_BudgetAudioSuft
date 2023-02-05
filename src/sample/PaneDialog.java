package sample;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaneDialog {
    private HBox root1 = new HBox(), root2 = new HBox();
    private VBox root = new VBox();
    public static double HEIGHT = Main.HEIGHT/5, WIDTH = Main.WIDTH/5;

    public static void main(String[] arg){
    }
    public Parent mainContent(){
        Label label = new Label("ENTER NAME: ");
        root1.getChildren().add(label);
        TextField textField = new TextField();
        textField.setEditable(true);
        root1.getChildren().add(textField);
        Button submit = new Button("SUBMIT");
        Button close = new Button("CLOSE");
        root2.getChildren().add(close);
        root2.getChildren().add(submit);
        submit.setOnMouseClicked(e->{
            Highscore.SCORE.add(new score(textField.getText(),PaneScore.score));
            Highscore.save();
            new Highscore();
            Main.stageDia.close();
        });
        close.setOnMouseClicked(e->{
            Main.stageDia.close();
        });
        root.getChildren().addAll(root1,root2);
        root.setPrefSize(WIDTH, HEIGHT);
        return root;
    }
}
