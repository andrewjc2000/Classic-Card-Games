//File created by Andrew Chafos: 8/21/15 @ 5:16 PM
package V1;

import V1.component.MainFrame;

public class Launcher {
    
    private static MainFrame frame;
    
    public static void main(String args[]){
        setup();
        start();
    }
    
    private static void setup(){
        frame = new MainFrame();
        frame.init();
    }
    
    private static void start(){
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            frame.run();
        });
       
    }
    
}
