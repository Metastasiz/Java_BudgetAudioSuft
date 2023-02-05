package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Prompt {
    public static void display(String title, String msg){
        Stage window = new Stage();
        //
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(Main.WIDTH/20);

        Label label = new Label();
        label.setText(msg);
        Button confirm = new Button("Confirm");
        confirm.setOnAction(e ->{

        });

    }
}
