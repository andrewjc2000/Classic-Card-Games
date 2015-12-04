//File created by Andrew Chafos: 8/21/15 @ 8:48 PM
package V1.games;

import V1.card.*;
import V1.component.*;
import V1.Globals;
import V1.anim.FixedAnimation;
import V1.util.Timing;
import java.awt.*;

//The first (and hopefully one of the, plural) game(s)
//to be in AJC Classic Card Games!
public class Cribbage extends GameComponent{
    
    private final Deck deck;
    private final Player player1;
    private final Player player2;
    private final V1.component.drawn.Image cardBack;
    private boolean inStartAnimState, inCribChoosingState;
    private int timeElapsed, startAnimProgress;
    
    public Cribbage(){
        deck = new Deck();
        deck.shuffle(3);
        
        player1 = new Player();
        player2 = new Player();
        cardBack = new V1.component.drawn.Image(Globals.cardBackImage, 0, 0);
        
        for(int i = 0;i < 6;i++){
            player1.addCardToHand(deck.accessRand());
            player2.addCardToHand(deck.accessRand());
        }
        
        inStartAnimState = false;
        inCribChoosingState = false;
        timeElapsed = 0;
        startAnimProgress = 0;
        
    }//end of default constructor
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        cardBack.draw(g, 20, (int)((650 - Globals.cardBackImage.getHeight()) / 2));
        
        if(timeElapsed > FPS){
            if(inStartAnimState){
                for(int i = 0; i < 6;i++){
                    int x = (int)(startAnimProgress * (170 + (i * 120)) / (FPS * 2));
                    int y = (int)((650 - Globals.cardBackImage.getHeight()) / 2);
                    cardBack.draw(g, x, y - (int)(startAnimProgress * (y - 20) / (FPS * 2)));
                    player1.getHand().get(i).image.draw(g, x, y + (startAnimProgress * (460 - y) / (FPS * 2)));
                }
                if(startAnimProgress < FPS * 2){
                    startAnimProgress++;
                }
                else{
                    startAnimProgress = 0;
                    inStartAnimState = false;
                    inCribChoosingState = true;
                }
            }
            else if(inCribChoosingState){
                for(int i = 0; i < 6;i++){
                    cardBack.draw(g, 170 + (i * 120), 20);
                    player1.getHand().get(i).image.draw(g, 170 + (i * 120), 460);
                } 
            }
        }
        else if(timeElapsed == FPS){
            inStartAnimState = true;
        }
        
        timeElapsed++;
        
    }//end of method
    
}//end of class
