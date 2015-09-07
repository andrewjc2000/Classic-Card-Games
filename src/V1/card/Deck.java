//File created by Andrew Chafos: 9/6/15 @ 3:40 PM
package V1.card;

import java.util.*;

public class Deck{
    
    private final ArrayList<Card> cards;
    
    public Deck(){
        cards = new ArrayList<>();
        for(int i = 0;i < 52;i++){
            cards.add(new Card());
        }
    }
    
    public void shuffle(){
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
    }
    
    public Card getCard(int num){
        try{
            return cards.get(num);
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public ArrayList<Card> getDeck(){
        return cards;
    }
    
}
