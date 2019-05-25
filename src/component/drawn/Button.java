//File created by Andrew Chafos: 8/27/15 @ 4:46 PM
package V1.component.drawn;

import java.awt.*;
import V1.exception.UnretrievableAttributeException;

//This class is a rectangle which gives information
//so that another class can use it to do something,
//however it doesn't do anything but change & display itself
public class Button extends Component{
    
    private final int x, y, width, height;
    private final boolean hasBorder;
    private boolean usingBorder;
    private final Border border;
    
    //I thought that a simple "get" method would be more efficient for 
    //several attributes of the same type which need to be accessed.
    public int get(String attribute){
        switch(attribute){
            case "x":
                return x;
            case "y":
                return y;
            case "width":
                return width;
            case "height":
                return height;
            default:
                throw new UnretrievableAttributeException(attribute, "Button");
        }//end of switch
    }//end of method
    
    public boolean getBool(String attribute){
        switch(attribute){
            case "hasBorder":
                return hasBorder;
            case "usingBorder":
                return usingBorder;
            default:
                throw new UnretrievableAttributeException(attribute, "Button");
        }
    }
    
    public Button(int x, int y, int width, int height, Color buttonColor){
        super(buttonColor);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        border = null;
        usingBorder = false;
        hasBorder = false;
    }
    
    public Button(int x, int y, int width, int height, Color buttonColor, Border border){
        super(buttonColor);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.border = border;
        hasBorder = true;
        usingBorder = false;
    }
    
    public void changeBorderUse(boolean use){
        usingBorder = use;
    }
    
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        if(usingBorder){
            border.draw(g);
        }
    }
    
    //this was originially created for mouse position, but you
    //can pretty much see if any coordinate is inside the rectangle
    //based on the rectangle's attributes
    public boolean containsCoords(int coordX, int coordY){
        return (coordX >= x && coordX <= x + width) &&
               (coordY >= y && coordY <= y + height);
    }
}//end of class
