//File created by Andrew Chafos: 9/6/15 @ 3:40 PM
package V1.card;

import java.util.*;

public class Deck{
    
    private final ArrayList<Card> cards;
    
    public Deck(){
        cards = new ArrayList<>();
        for(int i = 0;i < 4;i++){
            for(int it = 0;it < 13;it++){
                cards.add(new Card(it, i, 0, 0));
            }
        }
    }
    
    public void shuffle(){
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
    }
    
    public void shuffle(int numOfTimes){
        for(int i = 0; i < numOfTimes; i++){
            long seed = System.nanoTime();
            Collections.shuffle(cards, new Random(seed));
        }
    }
    
    public Card accessRand(){
        int num = Math.round((int)(Math.random() * 51));
        while(cards.get(num).getUsage()){
            num = Math.round((int)(Math.random() * 51));
        }
        cards.get(num).setUse(true); 
        return cards.get(num);
    }
    
    public void resetDeck(){
        for(int i = 0;i < 52; i++){
            if(cards.get(i).getUsage()){
                cards.get(i).setUse(false);
            }
        }
    }
    
    public ArrayList<Card> getDeck(){
        return cards;
    }
    
}
