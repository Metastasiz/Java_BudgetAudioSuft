package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteImg extends ImageView {
    final double accV = 1.05;
    final double accS1 = 1.034, accS2 = 1.03;
    final double iniSpd = 0.01;
    static double mul = 1.5;
    final String type;
    boolean show = true, left = false, right = false;
    double vector;
    double angle = 0;
    double currentVX = 0, currentVY = 0;
    double sizeX = 1, sizeY = 1;
    double totalX = 0, totalY = 0;
    double originX, originY;public SpriteImg(String type, double x, double y, double w, double h, Image img) {
        super();
        setImage(img);
        setPreserveRatio(false);
        setFitHeight(h);
        setFitWidth(w);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
        originX = getTranslateX();
        originY = getTranslateY();
        setTranslateX(originX - getFitWidth()/2);
        setTranslateY(originY - getFitHeight()/2);
    }
    void incSize(){
        //double rad = (vector/360)*Math.PI*2;
        //double m = Math.abs(Math.sin(rad));
        if (vector == 270){
            sizeY *= accS1;
            sizeX *= accS1;
        }
        else{
            sizeY *= accS2;
            sizeX *= accS2;
        }
        setFitWidth(sizeX);
        setFitHeight(sizeY);
    }
    void setVector(double v){
        v=v%(360);
        vector = v;
        double rad = (vector/360)*Math.PI*2;
        currentVX = Math.cos(rad)* iniSpd *mul;
        currentVY = Math.sin(rad)* iniSpd *mul;
    }


    void moveAuto(){
        currentVX *= accV;
        currentVY *= accV;
        totalX += currentVX; totalY += currentVY;
        setTranslateX(originX + totalX - getFitWidth()/2);
        setTranslateY(originY - totalY - getFitHeight()/2);
    }
}
