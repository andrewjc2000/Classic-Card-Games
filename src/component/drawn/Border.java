//File created by Andrew Chafos: 12/18/15 @ 9:34 PM
package V1.component.drawn;

import java.awt.*;

public class Border {
    
    private final Color color;
    private int width, height;
    private final int x, y, thickness;
    
    public void changeWidth(int w){
        width = w;
    }
    
    public void changeHeight(int h){
        height = h;
    }
    
    public Border(Color c, int x, int y, int width, int height, int thickness){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        color = c;
    }
    
    public void draw(Graphics g){
        g.setColor(color);
        for(int i = 1;i <= thickness;i++){
            g.drawRect(x - i, y - i, width + (i * 2) - 1, height + (i * 2) - 1);
        }
    }//end of method
    
}//end of class
