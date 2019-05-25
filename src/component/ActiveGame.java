//File created by Andrew Chafos: 8/21/15 @ 5:19 PM
package component;

import java.awt.*;
import javax.swing.*;
import games.*;
import java.awt.event.*;

//This class is the jPanel added to MainFrame which handles the switching
//and displaying of the current component
public class ActiveGame extends JPanel implements ActionListener{

    public boolean running;
    private boolean started;//same as initialized or init in other classes
    private GameComponent currentComponent;
    private final Timer timer;
    
    public ActiveGame(){
        running = true;
        started = false;
        timer = new Timer(5, this);
    }
    
    public void start(){
        if(!started){
            setLayout(new BorderLayout());//wouldn't work without this
            currentComponent = new MainMenu();
            currentComponent.changeTimer(true);
            addMouseListener(currentComponent);
            addMouseMotionListener(currentComponent);
            add(currentComponent, BorderLayout.CENTER);//and this
            timer.start();
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
        javax.swing.SwingUtilities.invokeLater(() -> {
            currentComponent.changeTimer(true);
            addMouseListener(currentComponent);
            addMouseMotionListener(currentComponent);
            add(currentComponent, BorderLayout.CENTER);
            validate();//and this was the solution to a problem I encountered
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(running){
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
                    case BLACKJACK:
                        removeCurrentComponent();
                        currentComponent = new BlackJack();
                        addCurrentComponent();
                        break;
                }//end of switch
            }//end of inner if
        }//end of outer if
        else{
            timer.stop();
        }
    }//end of overriden method
    
}//end of class
