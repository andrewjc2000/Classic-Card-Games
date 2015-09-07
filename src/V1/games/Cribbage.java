//File created by Andrew Chafos: 8/21/15 @ 8:48 PM
package V1.games;

import V1.card.Card;
import V1.component.*;
import java.awt.*;

//The first (and hopefully one of the, plural) game(s)
//to be in AJC Classic Card Games!
public class Cribbage extends GameComponent{
    
    Card testCard = new Card();
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        g.setColor(Color.white);
        g.drawString("Cribbage", 30, 30);
        testCard.drawCard(g, 0, 0);
    }
    
}//end of class
