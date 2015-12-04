//File created by Andrew Chafos: 11/28/15 @ 4:46 PM
package V1.util;

public class Timing {
    
    public static void delayCode(int time){//time is in millis
        
        long currentTime = System.currentTimeMillis();
        long objTime = currentTime + time;
        
        while(currentTime < objTime){
            currentTime = System.currentTimeMillis();
        }
        
    }
   
    
}
