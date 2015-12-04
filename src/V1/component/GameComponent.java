//File created by Andrew Chafos: 8/21/15 @ 9:19 PM
package V1.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import V1.Globals;
import V1.component.drawn.Image;

//GameComponent has subclasses.  That's why it implements several interfaces
//but does not use them; they are simply an option for its subclasses to choose
//to use.
public class GameComponent extends JComponent implements ActionListener, MouseListener,
    MouseMotionListener {
    
    protected final int FPS;
    private final Timer timer;
    private final V1.component.drawn.Image background;
    
    //These states are used by several classes, not just
    //GameComponent and its subclasses.
    protected enum STATE{
        ACTIVE, 
        INACTIVE
    };
    
    protected enum SWITCHTO{
        NONE,    //When this is set, and a component turns inactive, the program 
                //will end
        MAIN_MENU,
        CRIBBAGE
    }
    
    protected STATE state;
    protected SWITCHTO switchComp;
    
    public GameComponent(){
        FPS = 100;
        state = STATE.ACTIVE;
        switchComp = SWITCHTO.NONE;
        timer = new Timer((int)(1000 / FPS), this);
        background = new Image(Globals.BGImage, 0, 0);
    }
    
    public void changeTimer(boolean on){
        if(on && !timer.isRunning()){
            timer.start();
        }
        else if(!on && timer.isRunning()){
            timer.stop();
        }
    }
    
    //this method triggers the removal of the current component
    //and states which component it would like to switch to, unless
    //if it is signaling for the program to end, in which case it uses 
    //SWITCHTO.NONE
    protected void dispose(SWITCHTO comp){
        changeTimer(false);
        switchComp = comp;
        state = STATE.INACTIVE;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
    }
    
    //All of the AWT methods are down below :)
    //These methods are here because they are handy for the components to use
    protected void drawBackground(Graphics g){
        background.draw(g);
    }
    
    protected void drawBackground(Graphics g, Color color){
        g.setColor(color);
        g.fillRect(0, 0, Globals.frameWidth, Globals.frameHeight);
    }
    
    //This method appears in other places, but it can be used by the components by
    //default as well
    public void drawCenteredString(Graphics g, String s, boolean horizontal, int otherCoord){
        
        if(horizontal){
            int stringWidth = g.getFontMetrics().stringWidth(s);
            g.drawString(s, (int)((Globals.frameWidth - stringWidth) / 2), otherCoord);
        }
        else{
            int stringHeight = g.getFontMetrics().getHeight();
            g.drawString(s, otherCoord, (int)((Globals.frameHeight - stringHeight) / 2));
        }
    }
    
    //See top of this source file for explanation as to why
    //these methods are here
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}//end of class
