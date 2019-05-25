//File created by Andrew Chafos: 11/26/15 @ 10:25 AM
package card;

public class Player {
    
    public final Deck currentHand;
    
    public Player(){
        currentHand = new Deck(false);
    }//end of default constructor
    
    public void addCardToHand(Card card){
        currentHand.addCard(card);
    }
    
    public void removeCard(Card card){
        currentHand.removeCard(card);
    }
    
    public void emptyHand(){
        for(Card c: currentHand.getDeck()){
            currentHand.removeCard(c);
        }
    }
    
}//end of class
