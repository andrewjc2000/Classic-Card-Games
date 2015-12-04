//File created by Andrew Chafos: 11/26/15 @ 10:25 AM
package V1.card;

import java.util.ArrayList;

public class Player {
    
    private ArrayList<Card> currentHand;
    
    public ArrayList<Card> getHand(){
        return currentHand;
    }
    
    public Player(){
        currentHand = new ArrayList<>();
    }//end of default constructor
    
    public void addCardToHand(Card card){
        currentHand.add(card);
    }
    
    public void removeCard(int num){
        currentHand.remove(num);
    }
    
    public void emptyHand(){
        currentHand = new ArrayList<>();
    }
    
}//end of class
