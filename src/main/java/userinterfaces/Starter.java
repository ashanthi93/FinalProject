package userinterfaces;

import java.io.IOException;

/**
 * Created by Ashi on 8/9/2017.
 */
public class Starter {

    public static void main(String args[]) throws IOException{

        WelcomeWindow home = new WelcomeWindow();
        home.setVisible(true);
        home.setSize(450,450);
    }
}
