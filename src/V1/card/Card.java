//File created by Andrew Chafos: 8/30/15 @ 3:46 PM
package V1.card;

import V1.Globals;
import V1.component.drawn.Image;
import V1.util.Strings;

public class Card{
    
    private final int value;
    private final int suit;
    private final String vString;
    private final String sString;
    public final Image image;
    private boolean inUse;
    
    public int getValue(){
        return value;
    }
    
    public int getSuit(){
        return suit;
    }
    
    public String getStringValue(){
        return vString;
    }
    
    public String getStringSuit(){
        return sString;
    }
    
    public boolean getUsage(){
        return inUse;
    }
    
    public void setUse(boolean use){
        inUse = use;
    }
    
    public Card(int value, int suit, int posX, int posY){
        this.value = value;
        this.suit = suit;
        this.sString = CardGlobals.SUIT[suit];
        this.vString = CardGlobals.VALUE[value];
        
        int[] imgCoords = imagePosition(suit, value);
        
        image = new Image(
             Globals.deckImage, imgCoords[0], imgCoords[1], imgCoords[2], imgCoords[3], posX, posY
        );
    }
    
    //returns what we would call the name of this card
    //i.e. 2 of clubs, Ace of spades, etc.
    public String getFullName(){
        return Strings.capitalize(vString) + " of " + sString.toLowerCase();
    }
    
    private int[] imagePosition(int suit, int value){//both of these start from 0 and go to 12 or 3
        int startingX = (int)(Math.round((value * Globals.deckImage.getWidth()) / 13));
        int startingY = (int)(Math.round((suit * Globals.deckImage.getHeight()) / 4));
        int endingX = startingX + (int)(Math.round(Globals.deckImage.getWidth() / 13));
        int endingY = startingY + (int)(Math.round(Globals.deckImage.getHeight() / 4));        
        int[] coords = {startingX, startingY, endingX, endingY};
        return coords;
    }
    
}//end of class
