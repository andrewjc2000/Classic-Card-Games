//File created by Andrew Chafos: 8/27/15 @ 4:54 PM
package V1.exception;

public class GameAWTComponentException extends RuntimeException{
    
    public GameAWTComponentException(){
        super("An exception occurred involving an AWT component of an AJC Classic Card Game class!");
    }
    
    public GameAWTComponentException(String msg){
        super(msg);
    }

}
