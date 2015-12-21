//File created by Andrew Chafos: 8/28/15 @ 6:55 PM
package V1.component.drawn;

import V1.Globals;
import java.awt.*;

//A Label is simply a String with fancy variables
//which can be drawn on an AWT component.
public class Label extends Component{
    
    private int x, y;
    public String text;
    private final Font font;
    
    //this constructor takes in a Font object...
    public Label(int x, int y, String text, Font font, Color color){
        super(color);
        this.x = x;
        this.y = y;
        this.text = text;
        this.font = font;
    }
    
    //while this one only takes in the font size and String for the font
    //and assumes you want to use a plain font
    public Label(int x, int y, String text, String font, int fontSize, Color color){
        super(color);
        this.x = x;
        this.y = y;
        this.text = text;
        this.font = new Font(font, Font.PLAIN, fontSize);
    }
    
    public void draw(Graphics g){
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
    }
    
    //can draw a string horizontally or vertically centered based on the frame's
    //width and height.  These attributes have already been initialized at the beginning
    //of the program in the class V1.Globals
    public void drawCenteredString(Graphics g, boolean horizontal, int otherCoord){
        g.setColor(color);
        g.setFont(font);
        if(horizontal){
            int stringWidth = g.getFontMetrics().stringWidth(text);
            g.drawString(text, (int)((Globals.frameWidth - stringWidth) / 2), otherCoord);
        }
        else{
            int stringHeight = g.getFontMetrics().getHeight();
            g.drawString(text, otherCoord, (int)((Globals.frameHeight - stringHeight) / 2));
        }
        //possibly confusing math is used for centering the string
    }
    
    //this will center the label inside of the button
    public void drawInButton(Graphics g, Button b){
        b.draw(g);
        g.setColor(color);
        g.setFont(font);
        int stringWidth = g.getFontMetrics().stringWidth(text);
        int stringHeight = g.getFontMetrics().getHeight();
        g.drawString(text, b.get("x") + (int)((b.get("width") - stringWidth) / 2), 
            b.get("y") + (int)(3 * (b.get("height") - stringHeight) / 2));
        //confusing math just uses the button and the string's heights & widths
        //to determine the position for the label.
    }
    
    public void drawInRect(Graphics g, Color rectColor){
        g.setColor(rectColor);
        g.setFont(font);        
        g.fillRect(x - 5, y - (int)(3 * g.getFontMetrics().getHeight() / 4) - 5, 
            g.getFontMetrics().stringWidth(text) + 10, g.getFontMetrics().getHeight() + 10);
        draw(g);
    }
    
    public void changeX(int newX){
        this.x = newX;
    }
    
    public void changeY(int newY){
        this.y = newY;
    }
    
}//end of class
