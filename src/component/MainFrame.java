//File created by Andrew Chafos: 8/21/15 @ 5:18 PM
package component;

import javax.swing.*;
import java.awt.*;
import resources.Globals;

//This is the main JFrame where everything gets done,
//however it only uses a single JPanel, ActiveGame.
//ActiveGame handles the adding and removing of components.
public class MainFrame extends JFrame{

    private final int width, height;
    private boolean init;  //this variable ensures the frame isn't initialized
                           // twice by accident.
    private ActiveGame gamePanel;
    
    //You can initialize with or without a frame width/height.
    //The default is width=1000 and height=650
    public MainFrame(){
        this.width = 1000;
        this.height = 650;
        Globals.frameWidth = 1000;
        Globals.frameHeight = 650;
        init = false;
    }
    
    public MainFrame(int width, int height){
        this.width = width;
        this.height = height;
        Globals.frameWidth = width;
        Globals.frameHeight = height;
        init = false;
    }
    
    public void init(){
        if(!init){
            setTitle("AJC Classic Card Games Version 1.0");
            setSize(1000, 650);
            setResizable(false);
            setSize(1000, 650);
            setLocationRelativeTo(null);
            setMinimumSize(new Dimension(1000, 650));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            init = true;
        }
    }
    
    //note that the gamePanel object is not initialized until the frame
    //actually starts.
    public void run(){
        gamePanel = new ActiveGame();
        gamePanel.start();
        getContentPane().add(gamePanel);
        pack();
        setVisible(true);
    }
}
