package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Highscore implements Serializable{
    static List<score> SCORE = new ArrayList<>(), SCORE2 = new ArrayList<>();
    final static String hsSave = "resources/saves/highscore.txt";
    final static File f = new File(hsSave);
    Highscore(){
        Comparator<score> comp2 = new Comp2();
        Collections.sort(SCORE,comp2);
    }
    public static void main(String[] arg){
        //SCORE.add(new score("name",3));
        //SCORE.add(new score("name",6));
        //SCORE.add(new score("name",4));
        //SCORE.add(new score("name",7));
        load();
        new Highscore();
        for (score e : SCORE) System.out.println(e.name + " " + e.score);
        save();
    }
    static void load(){
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            SCORE = (List<score>)ois.readObject();
        }
        catch(Exception r){}
    }
    static void save(){
        System.out.println("save");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(SCORE);
        }
        catch(Exception r){}
    }
}
class score implements Serializable{
    String name;
    int score;
    score(String n, int s){name=n;score=s;}
}
class Comp2 implements Comparator<score> {
    @Override
    public int compare(score o1, score o2) {
        if (o1.score!=o2.score)return o2.score-o1.score;
        else return o1.name.compareToIgnoreCase(o2.name);
    }
}
