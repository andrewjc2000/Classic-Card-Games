//File created by Andrew Chafos: 8/21/15 @ 8:48 PM
package V1.games;

import V1.component.*;
import java.awt.*;

//The first (and hopefully one of the, plural) game(s)
//to be in AJC Classic Card Games!
public class Cribbage extends GameComponent{
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        g.setColor(Color.white);
        g.drawString("Cribbage", 30, 30);
        //prob. need to switch this to a label later if keeping at all ^
    }
    
}//end of class
