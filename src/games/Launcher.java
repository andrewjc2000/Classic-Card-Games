package games;//File created by Andrew Chafos: 8/21/15 @ 5:16 PM

import component.MainFrame;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import resources.Globals;

public class Launcher {
    
    private static MainFrame frame;
    
    public static void main(String args[]){
        setup();
        start();
    }
    
    private static void setup(){
        frame = new MainFrame();
        frame.init();
        
        try{
            URL resource1 = Launcher.class.getClassLoader()
                .getResource("resources/images/deck.jpg");
            BufferedImage deck = ImageIO.read(resource1);
            Globals.deckImage = deck;
            URL resource2 = Launcher.class.getClassLoader()
                .getResource("resources/images/WoodBG.jpg");
            BufferedImage bg = ImageIO.read(resource2);
            Globals.BGImage = bg;
            URL resource3 = Launcher.class.getClassLoader()
                .getResource("resources/images/CardBack.jpg");
            BufferedImage cardBack = ImageIO.read(resource3);
            Globals.cardBackImage = cardBack;
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private static void start(){
        javax.swing.SwingUtilities.invokeLater(() -> frame.run());
    }
    
}
