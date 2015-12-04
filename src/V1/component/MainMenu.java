//File created by Andrew Chafos: 8/27/15 @ 2:50 PM
package V1.component;

import java.awt.*;
import java.awt.event.*;
import V1.component.drawn.Button;
import V1.component.drawn.Label;
import V1.Globals;

//This file is the main menu component which is the component at start-up
//and can also be switchted to during a game
public class MainMenu extends GameComponent{
    
    //As initialized in the constructor, the first button is
    //to play Cribbage, and the second one is to exit the program.
    
    private final Button[] buttons;
    private final Label[] labels;
    
    public MainMenu(){
        
        buttons = new Button[2];
        buttons[0] = new Button((Globals.frameWidth - 200) / 2, 200, 200, 80, Color.BLUE);
        //height = 80, width = 200
        buttons[1] = new Button((Globals.frameWidth - 200) / 2, 300, 200, 80, Color.BLUE);
        //height = 80, width = 200
        
        labels = new Label[3];
        Font font = new Font("Arial", Font.BOLD, 40);
        labels[0] = new Label((Globals.frameWidth - 200) / 2, 200, "Cribbage", font, Color.white);
        labels[1] = new Label((Globals.frameWidth - 200) / 2, 300, "Exit", font, Color.white);
        Font titleFont = new Font("Monospaced", Font.BOLD, 80);
        labels[2] = new Label(200, 75, "MAIN MENU", titleFont, Color.white);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g, Color.black);
        
        for(int i = 0;i < 2;i++){
            buttons[i].draw(g);
            labels[i].drawInButton(g, buttons[i]);
        }
        
        //this is the title Label (says MAIN MENU).  It is the last in the array of
        //Label objects because it improves the efficiency of the for loop above.
        
        labels[2].drawCenteredString(g, true, 150);
    }//end of method

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        if(buttons[0].containsCoords(mouseX, mouseY)){
            dispose(SWITCHTO.CRIBBAGE);
        }
        else if(buttons[1].containsCoords(mouseX, mouseY)){
            dispose(SWITCHTO.NONE);
        }
        
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        //highlights the buttons based on whether the mouse is hovering over.
        //there is a highlighted variable in the components so that the
        //method isn't called every time the mouse moves.
        if(buttons[0].containsCoords(mouseX, mouseY) && !buttons[0].getHighlighted()){
            buttons[0].highlightComponent(true);
        }
        if(buttons[1].containsCoords(mouseX, mouseY) && !buttons[1].getHighlighted()){
            buttons[1].highlightComponent(true);
        }
        if(!buttons[0].containsCoords(mouseX, mouseY) && buttons[0].getHighlighted()){
            buttons[0].highlightComponent(false);
        }
        if(!buttons[1].containsCoords(mouseX, mouseY) && buttons[1].getHighlighted()){
            buttons[1].highlightComponent(false);
        }
        
    }//end of method
    
}//end of class
