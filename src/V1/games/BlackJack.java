//File created by Andrew Chafos: 3/15/17 @ 1:08 PM

package V1.games;

import V1.Globals;
import V1.card.Card;
import V1.card.Deck;
import V1.component.GameComponent;
import V1.component.drawn.Button;
import V1.component.drawn.Label;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BlackJack extends GameComponent{
    
    private enum State {
        INIT_DELAY,
        TWO_CARD_ANIM,
        HIT_OR_STAY,
        HIT,
        BUST_TRANSITION,
        STAY,
        DEALER_MOVE
    };
    
    private final Deck deck;
    private final V1.component.drawn.Image cardBack;
    private int counter, initCardAnim, currentSum, newCardAnim, moveText;
    private State currentState;
    private final ArrayList<Card> playerHand;
    private final Button hit, stay;
    private final Label hitOrStay, hitL, stayL, playerSum;
    
    public BlackJack(){
        deck = new Deck(true);
        cardBack = new V1.component.drawn.Image(Globals.cardBackImage, 0, 0);
        counter = 0;
        currentSum = 0;
        newCardAnim = 0;
        moveText = 0;
        currentState = State.INIT_DELAY;
        playerHand = new ArrayList<>();
        deck.shuffle(3);
        playerHand.add(deck.accessRand());
        playerHand.add(deck.accessRand());
        hitOrStay = new Label(400, 200, "Hit or Stay?", new Font("Monospaced", Font.BOLD, 64), Color.YELLOW);
        hit = new Button(250, 250, 150, 100, Color.green);
        hitL = new Label(250, 250, "Hit", new Font("Monospaced", Font.BOLD, 48), Color.WHITE);
        stay = new Button(550, 250, 150, 100, Color.red);
        stayL = new Label(250, 250, "Stay", new Font("Monospaced", Font.BOLD, 48), Color.WHITE);
        playerSum = new Label(400, 100, "Sum:", new Font("Monospaced", Font.BOLD, 64), Color.BLUE);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        drawBackground(g);
        cardBack.draw(g, 50, (650 - Globals.cardBackImage.getHeight()) / 2);
        double h = Globals.cardBackImage.getHeight();
        double p = ((1.5 * FPS) - initCardAnim) / (1.5 * FPS);
        double p2 = (newCardAnim / (1.0 * FPS));
        double p3 = (moveText / (3.0 * FPS));
        
        if(currentState == State.TWO_CARD_ANIM){
            cardBack.draw(g, 50 + (int)(50 * p), 
                (int)((0.5 * (650 - h) + p * ((325 * (7.0/8.0)) - (0.5 * h)))));
            cardBack.draw(g, 50 + (int)(100 * p), 
                (int)((0.5 * (650 - h) + p * ((325 * (7.0/8.0)) - (0.5 * h)))));
        }
        else if(currentState == State.HIT_OR_STAY){
            for(int i = 0;i < playerHand.size();i++){
                playerHand.get(i).image.draw(g, 100 + (i * 50), (int)(325 + (int)(325 * (7.0/8.0)) - h));
            }
            hitOrStay.drawCenteredString(g, true, 200);
            hit.draw(g);
            hitL.drawInButton(g, hit);
            stay.draw(g);
            stayL.drawInButton(g, stay);
            playerSum.drawCenteredString(g, true, 100);
        }
        else if(currentState == State.HIT){
            for(int i = 0;i < playerHand.size() - 1;i++){
                playerHand.get(i).image.draw(g, 100 + (i * 50), (int)(325 + (int)(325 * (7.0/8.0)) - h));
            }
            cardBack.draw(g, 100 + (int)((playerHand.size() - 1) * 50 * p2), 
                (int)((0.5 * (650 - h) + p2 * ((325 * (7.0/8.0)) - (0.5 * h)))));
            
        }
        else if(currentState == State.BUST_TRANSITION || currentState == State.STAY){
            for(int i = 0;i < playerHand.size();i++){
                playerHand.get(i).image.draw(g, 100 + (i * 50), (int)(325 + (int)(325 * (7.0/8.0)) - h));
            }
            playerSum.drawCenteredString(g, true, 100 + (int)(p3 * 350));
        }
        else if(currentState == State.DEALER_MOVE){
            for(int i = 0;i < playerHand.size();i++){
                playerHand.get(i).image.draw(g, 100 + (i * 50), (int)(325 + (int)(325 * (7.0/8.0)) - h));
            }
            playerSum.drawCenteredString(g, true, 450);
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
        if(currentState == State.INIT_DELAY){
            //4 second delay
            if(counter < 1 * FPS){
                counter++;
            }
            else{
                counter = 0;
                currentState = State.TWO_CARD_ANIM;
                initCardAnim = (int)(1.5 * FPS);
            }
        }
        else if(currentState == State.TWO_CARD_ANIM){
            if(initCardAnim > 0){
                initCardAnim--;
            }
            else{
                initCardAnim = 0;
                currentSum = getSum();
                playerSum.text = "Sum: " + currentSum;
                currentState = State.HIT_OR_STAY;
            }
        }
        else if(currentState == State.HIT){
            if(newCardAnim < 1 * FPS){
                newCardAnim++;
            }
            else{
                newCardAnim = 0;
                if(currentSum > 21){
                    currentState = State.BUST_TRANSITION;
                }
                else{
                    currentState = State.HIT_OR_STAY;
                }
            }
        }
        else if(currentState == State.BUST_TRANSITION || currentState == State.STAY){
            if(moveText < 3 * FPS){
                moveText++;
            }
            else{
                currentState = State.DEALER_MOVE;
            }
        }
    }
    
    private int getSum(){
        int sum = 0;
        for(Card c: playerHand){
            sum += c.getCribbageValue();
        }
        if(sum > 21){
            for(int i = 0;(i < playerHand.size() && sum > 21);i++){
                if(playerHand.get(i).getValue() == 0){
                    sum -= 10;
                }
            }
        }
            
        return sum;
    }
    
    private void hit(){
        playerHand.add(deck.accessRand());
        currentSum = getSum();
        playerSum.text = ((currentSum > 21) ? "You bust! Sum: " : "Sum: ") + currentSum;
    }
    
    private void stay(){
        playerSum.text = "You stayed.  Sum: " + currentSum;
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        if(currentState == State.HIT_OR_STAY){
            if(hit.containsCoords(mouseX, mouseY)){
                currentState = State.HIT;
                hit();
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            if(stay.containsCoords(mouseX, mouseY)){
                currentState = State.STAY;
                stay();
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        if(currentState == State.HIT_OR_STAY){
        //for(int i = 0;i < buttons.length;i++){
            if(hit.containsCoords(mouseX, mouseY) && !hit.getHighlighted()){
                hit.highlightComponent(true);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            if(!hit.containsCoords(mouseX, mouseY) && hit.getHighlighted()){
                hit.highlightComponent(false);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            if(!stay.containsCoords(mouseX, mouseY) && stay.getHighlighted()){
                stay.highlightComponent(false);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            if(stay.containsCoords(mouseX, mouseY) && !stay.getHighlighted()){
                stay.highlightComponent(true);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        //}
        }
    }//end of method
}
