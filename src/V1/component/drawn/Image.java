//File created by Andrew Chafos: 9/7/15 @ 3:15 PM
package V1.component.drawn;

import java.awt.*;
import java.awt.image.*;

public class Image {
    
    private final BufferedImage image;
    private final IMG_TYPE type;
    private final int width, height, sX, sY, eX, eY;//s = starting, e = ending
    private int posX, posY;
    
    private enum IMG_TYPE {
        ONLY_IMAGE,
        DIMENSIONS,//snipit of the image that starts from the left-hand corner
        SNIPIT //snipit means that you take a piece of an image that doesn't start at the 
               //left hand corner; it starts and ends at two points in the image.
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public Image(BufferedImage img, int posX, int posY){
        image = img;
        width = img.getWidth();
        height = img.getHeight();
        type = IMG_TYPE.ONLY_IMAGE;
        
        sX = 0;
        sY = 0;
        eX = width;
        eY = height;
        this.posX = posX;
        this.posY = posY;
    }
    
    public Image(BufferedImage img, int width, int height, int posX, int posY){
        image = img;
        this.width = width;
        this.height = height;
        type = IMG_TYPE.DIMENSIONS;
        
        sX = 0;
        sY = 0;
        eX = width;
        eY = height;
        
        this.posX = posX;
        this.posY = posY;
    }
    
    public Image(BufferedImage img, int startingX, int startingY, int endingX, int endingY,
            int posX, int posY
            ){
        image = img;
        type = IMG_TYPE.SNIPIT;

        sX = startingX;
        sY = startingY;
        eX = endingX;
        eY = endingY;
        
        this.width = endingX - startingX;
        this.height = endingY - startingY;
        
        this.posX = posX;
        this.posY = posY;
    }
    
    public void draw(Graphics g){
        g.drawImage(image, posX, posY, posX + width, posY + height, sX, sY, eX, eY, null);
    }
    
    public void draw(Graphics g, int x, int y){
        if(x != posX){
            posX = x;
        }
        if(y != posY){
            posY = y;
        }   
        g.drawImage(image, x, y, x + width, y + height, sX, sY, eX, eY, null);
        drawBorder(g);
    }
    
    public void drawBorder(Graphics g){
        g.setColor(Color.black);
        for(int i = 0;i < 3;i++){
            g.drawRect(posX - i, posY - i, width + (i * 2), height + (i * 2));
        }
    }
    
    public boolean containsCoords(int coordX, int coordY){
        return (coordX >= posX && coordX <= posX + width) &&
               (coordY >= posY && coordY <= posY + height);
    }
    
    public void changeX(int newX){
        this.posX = newX;
    }
    
    public void changeY(int newY){
        this.posY = newY;
    }
    
}
