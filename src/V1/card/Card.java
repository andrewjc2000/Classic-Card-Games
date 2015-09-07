//File created by Andrew Chafos: 8/30/15 @ 3:46 PM
package V1.card;

import V1.Globals;
import V1.util.Strings;
import java.util.Random;
import java.util.Arrays;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Card{
    
    private final String value;
    private final String suit;
    private final int numberValue;
    
    public String getValue(){
        return value;
    }
    
    public String getSuit(){
        return suit;
    }
    
    //Default constructor sets random values for the auit & value
    //while overloaded simply sets what it is given
    public Card(){
        
        long seed = System.nanoTime();
        
        int cardVal = new Random(seed).nextInt(CardGlobals.VALUE.length - 1);
        this.value = CardGlobals.VALUE[cardVal];
        this.numberValue = cardVal + 1;
        
        this.suit = CardGlobals.SUIT[
            new Random(seed).nextInt(CardGlobals.SUIT.length - 1)
        ];
    }
    
    public Card(String value, String suit){
        this.value = value;
        this.suit = suit;
        int cardVal = -1;
        
        //the purpose of this for loop is to iterate through the globals
        //to see if the value is valid
        for(int i = 0;i < CardGlobals.VALUE.length;i++){
            if(CardGlobals.VALUE[i].equals(suit)){
                cardVal = i + 1;
            }
        }
        
        this.numberValue = cardVal;
        if(numberValue == -1 || !Arrays.toString(CardGlobals.SUIT).contains(suit)){
            throw new RuntimeException("Card was initialized with invalid suit/value.  You suck.");
        }
        //I will complain if you pass through an invalid value--don't do it >:|
    }
    
    //returns what we would call the name of this card
    //i.e. 2 of clubs, Ace of spades, etc.
    public String getFullName(){
        return Strings.capitalize(value) + " of " + suit.toLowerCase();
    }
    
    public void drawCard(Graphics g, int x, int y){
        g.drawImage(Globals.deckImage, x, y, null);
    }
    
}//end of class
