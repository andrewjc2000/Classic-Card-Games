package resources;//File created by Andrew Chafos: 8/28/15 @ 9:27 PM

import java.awt.image.BufferedImage;

//This class contains Global attributes
//for every single class in the program to access
public class Globals {
    
    public static int frameWidth, frameHeight;
    public static BufferedImage deckImage, BGImage, cardBackImage;

    public final static String[] SUIT = {
        "CLUBS",
        "SPADES",
        "HEARTS",
        "DIAMONDS"
    };

    public final static String[] VALUE = {
        "ACE", "2", "3", "4",
        "5", "6", "7", "8", "9",
        "10", "JACK", "QUEEN", "KING"
    };

}//end of class
