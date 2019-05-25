//File created by Andrew Chafos: 8/28/15 @ 8:30 PM
package V1.component.drawn;

import java.awt.*;

//This is the superclass of any drawn component that
//helps any of the GameComponents
public class Component {
    
    //original & final colors are used for highlighting any subclass when they
    //so choose.  Same w/ the highlghted boolean.
    protected final Color originalColor, highlightedColor;
    protected Color color;
    private boolean highlighted;
    
    public boolean getHighlighted(){
        return highlighted;
    }
    
    public Component(Color color){
        highlighted = false;
        this.color = color;
        this.originalColor = color;
        this.highlightedColor = highlight(color);
    }
    
    public void highlightComponent(boolean doIt){
        if(doIt){
            highlighted = true;
            color = highlightedColor;
        }
        else{
            highlighted = false;
            color = originalColor;
        }
    }
    
    //this is only used in the constructor of
    //V1.component.drawn.Component because
    //Component is initialized with a preset highlighted color
    private Color highlight(Color colo){
        int red = colo.getRed();
        int green = colo.getGreen();
        int blue = colo.getBlue();
        
        if(red + 100 >= 255){
            red = 255;
        }
        else{
            red += 100;
        }
        
        if(green + 100 >= 255){
            green = 255;
        }
        else{
            green += 100;
        }
        
        if(blue + 100 >= 255){
            blue = 255;
        }
        else{
            blue += 100;
        }
        
        return new Color(red, green, blue);
    }//end of highlight method
    
}//end of class
