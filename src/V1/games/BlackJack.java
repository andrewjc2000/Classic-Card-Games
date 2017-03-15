//File created by Andrew Chafos: 3/15/17 @ 1:08 PM

package V1.games;

import V1.card.Deck;
import V1.component.GameComponent;
import java.awt.Color;
import java.awt.Graphics;

public class BlackJack extends GameComponent{
    
    private final Deck deck;
    
    public BlackJack(){
        deck = new Deck(true);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        drawBackground(g, Color.YELLOW);
    }
}
