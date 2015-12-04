//File created by Andrew Chafos: 8/21/15 @ 5:19 PM
package V1.component;

import java.awt.*;
import javax.swing.*;
import V1.games.*;

//This class is the jPanel added to MainFrame which handles the switching
//and displaying of the current component
public class ActiveGame extends JPanel implements Runnable{

    public boolean running;
    private boolean started;//same as initialized or init in other classes
    private GameComponent currentComponent;
    
    public ActiveGame(){
        running = false;
        started = false;
    }
    
    public void start(){
        if(!started){
            setLayout(new BorderLayout());//wouldn't work without this
            currentComponent = new MainMenu();
            currentComponent.changeTimer(true);
            addMouseListener(currentComponent);
            addMouseMotionListener(currentComponent);
            add(currentComponent, BorderLayout.CENTER);//and this
            new Thread(this).start();//this is just how I choose to do it
            started = true;
        }
    }
    
    private void removeCurrentComponent(){
        remove(currentComponent);
        removeMouseListener(currentComponent);
        removeMouseMotionListener(currentComponent);
        invalidate();//again, this
    }
    
    private void addCurrentComponent(){
        currentComponent.changeTimer(true);
        addMouseListener(currentComponent);
        addMouseMotionListener(currentComponent);
        add(currentComponent, BorderLayout.CENTER);
        validate();//and this was the solution to a problem I encountered
    }
    
    @Override
    public void run(){
        running = true;
        while(running){
            if(currentComponent.state == GameComponent.STATE.INACTIVE){
                switch(currentComponent.switchComp){
                    case NONE:
                        System.exit(0);
                        break;
                    case MAIN_MENU:
                        removeCurrentComponent();
                        currentComponent = new MainMenu();
                        addCurrentComponent();
                        break;
                    case CRIBBAGE:
                        removeCurrentComponent();
                        currentComponent = new Cribbage();
                        addCurrentComponent();
                        break;
                }//end of switch
            }//end of if
        }//end of while loop
    }//end of overriden method
    
    
}//end of class
