//File created by Andrew Chafos: 8/21/15 @ 8:48 PM
package V1.games;

import V1.card.*;
import V1.component.*;
import V1.Globals;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//The first (and hopefully one of the, plural) game(s)
//to be in AJC Classic Card Games!
public class Cribbage extends GameComponent{
    
    private final Deck deck;
    private final Player player1;
    private final Player player2;
    private final ArrayList<Card> currentCrib;
    private final V1.component.drawn.Image cardBack;
    private int timeElapsed, startAnimProgress, gameState;
    
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
        
        gameState = 0;
        timeElapsed = 0;
        startAnimProgress = 0;
        
        currentCrib = new ArrayList<>();
        
    }//end of default constructor
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        cardBack.draw(g, 20, (int)((650 - Globals.cardBackImage.getHeight()) / 2));
        
        if(timeElapsed > FPS){
            if(gameState == 1){
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
                    gameState = 2;
                }
            }
            else if(gameState == 2){
                for(int i = 0; i < 6;i++){
                    cardBack.draw(g, 170 + (i * 120), 20);
                    player1.getHand().get(i).image.draw(g);
                } 
            }
        }
        else if(timeElapsed == FPS){
            gameState = 1;
        }
        
        timeElapsed++;
        
    }//end of method
    
    @Override
    public void mouseMoved(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        if(gameState == 2){
            for(Card card: player1.getHand()){
                if(card.image.containsCoords(mouseX, mouseY) && 
                    !card.image.getHighlighted()
                ){
                    card.image.highlight(new Color(0, 255, 255, 128));
                }
                else if(!card.image.containsCoords(mouseX, mouseY) && 
                    card.image.getHighlighted()
                ){
                    card.image.deHighlight();
                }
            }//end of for loop
        }//end of if
        
    }//end of method
    
    @Override
    public void mouseClicked(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        if(gameState == 2){
            for(Card card: player1.getHand()){
                if(card.image.containsCoords(mouseX, mouseY) && !card.image.selected){
                    card.image.selected = true;
                    card.image.changeY(440);
                    currentCrib.add(card);
                }
                else if(card.image.containsCoords(mouseX, mouseY) && card.image.selected){
                    card.image.selected = false;
                    card.image.changeY(460);
                    currentCrib.remove(card);
                }
            }//end of for loop
        }//end of ifs
    }
    
}//end of class
