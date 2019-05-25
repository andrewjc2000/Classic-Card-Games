//File created by Andrew Chafos: 11/28/15 @ 1:40 PM
package anim;

import java.awt.*;
import javax.swing.JComponent;

public class FixedAnimation extends JComponent implements Runnable{
    
    private final Thread thread;
    private boolean started, running;
    
    public FixedAnimation(){
        started = false;
        running = false;
        thread = new Thread(this);
    }
        
    @Override
    public void run() {
        while(running){
            repaint();
        }
    }
    
    public void start(){
        started = true;
        running = true;
        thread.start();
    }
    
    public void stop(){
        
    }
    
    public boolean running(){
        return thread.isAlive();
    }
    
    @Override
    public void paintComponent(Graphics g){
    
    }
    
}
