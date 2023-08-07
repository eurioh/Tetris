package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread{
   
    public GameArea ga;
    
    public GameThread(GameArea ga) {
        this.ga = ga;
    }
    
    public void run() {
        //main game loop
        while(true) {
            
            ga.spawnBlock();
            
            while(ga.moveBlockDown() == true) { 
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
