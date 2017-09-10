package org.ucsc.sse.userinterfaces.swing_ui;

import java.io.IOException;

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
