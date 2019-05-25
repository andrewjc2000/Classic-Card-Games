//File created by Andrew Chafos: 12/19/15 @ 9:42 PM
package card;

import java.util.ArrayList;
import java.util.Arrays;

public class AIPlayer extends Player{
    
    public Card[] makeCribSelection(){
        
        int currentHS = -1;
        Card card1 = null;
        Card card2 = null;
        
        for(int i = 0;i < 5;i++){
            for(int it = 1 + i;it < 6;it++){
                int pointValue = getPoints(i, it); 
                if(pointValue > currentHS){
                    card1 = currentHand.getDeck().get(i);
                    card2 = currentHand.getDeck().get(it);
                    currentHS = pointValue;
                }
            }
        }
        
        currentHand.removeCard(card1);
        currentHand.removeCard(card2);
        
        Card[] cardList = {card1, card2};
        
        return cardList;
    }
    
    public int getPoints(int skipped1, int skipped2){
        int points = 0;
        
        ArrayList<Card> cards = new ArrayList<>();
        
        for(int i = 0;i < 6;i++){
            if(i != skipped1 && i != skipped2){
                cards.add(currentHand.getDeck().get(i));
            }
        }
        
        int[] v = {
            cards.get(0).getCribbageValue(), 
            cards.get(1).getCribbageValue(), 
            cards.get(2).getCribbageValue(), 
            cards.get(3).getCribbageValue()
        };
        
        Arrays.sort(v);
        
        int combo2[][] = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}};
        int combo3[][] = {{0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3}};
        
        for(int[] combo: combo2){
            if(v[combo[0]] + v[combo[1]] == 15){
                points += 2;
            }
            if(v[combo[0]] == v[combo[1]]){
                points += 2;
            }
        }
        
        boolean runof4 = false;
        
        if(v[0] == v[1] - 1 && v[1] == v[2] - 1 && v[2] == v[3] - 1){
            points += 4;
            runof4 = true;
        }
        
        for(int[] combo: combo3){
            if(v[combo[0]] + v[combo[1]] + v[combo[2]] == 15){
                points += 2;
            }
            if(!runof4){
                if(v[combo[0]] == (v[combo[1]] - 1) && v[combo[1]] == (v[combo[2]] - 1)){
                    points += 3;
                }
            }
        }
        
        if((cards.get(0).getSuit() == cards.get(1).getSuit()) && 
            (cards.get(1).getSuit() == cards.get(2).getSuit())
            && (cards.get(2).getSuit() == cards.get(3).getSuit())    
            ){
            points += 4;
        }
        
        return points;
    }
    
}
