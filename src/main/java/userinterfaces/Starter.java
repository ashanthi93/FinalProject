package userinterfaces;

import java.io.IOException;

/**
 * Created by Ashi on 8/9/2017.
 */
public class Starter {
    
    public static WelcomeWindow welcome;
    public static Settings settings;
    
    public static void main(String args[]) throws IOException{

        welcome = new WelcomeWindow();
        welcome.setVisible(true);
        welcome.setSize(450,450);
        
        settings = new Settings();
    }
}
