//File created by Andrew Chafos: 9/6/15 @ 3:40 PM
package card;

import java.util.*;

public class Deck{
    
    protected final ArrayList<Card> cards;
    private final boolean isFull;
    
    public Deck(boolean full){
        cards = new ArrayList<>();
        
        isFull = full;
        
        if(full){
            for(int i = 0;i < 4;i++){
                for(int it = 0;it < 13;it++){
                    cards.add(new Card(it, i, 0, 0));
                }
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
    
    public void addCard(Card newCard){
        if(!isFull && cards.size() < 52){
            cards.add(newCard);
        }
    }
    
    public void removeCard(Card card){
        if(!isFull && cards.size() < 52){
            cards.remove(card);
        }
    }
    
    public void sortCards(){  
        Collections.sort(cards, (card1, card2) -> card1.getValue() - card2.getValue());   
    }
    
    public void print(){
        System.out.println();
        for(Card c: cards){
            System.out.println(c.getFullName());
        }
    }
    
}
