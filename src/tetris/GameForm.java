package tetris;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class GameForm extends JFrame {

    private GameArea ga;
        public GameForm() {
        initComponents();
        
        ga= new GameArea(gameAreaPlaceholder, 10);
        
        //GameArea object added to the GameForm object
        this.add(ga);
        
        initControls();
        
        startGame();
    }
        
    //key control
    private void initControls() {
     //key bindings - binding input key and action in the game
     InputMap im = this.getRootPane().getInputMap();
     ActionMap am = this.getRootPane().getActionMap();
        
     im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
     im.put(KeyStroke.getKeyStroke("LEFT"), "left");
     im.put(KeyStroke.getKeyStroke("UP"), "up");
     im.put(KeyStroke.getKeyStroke("DOWN"), "down");
          
     //use anonymous class for repetitive use of abstrac class
     am.put("right", new AbstractAction(){
         @Override
         public void actionPerformed(ActionEvent e) {
            ga.moveBlockRight();
         }
     });
     am.put("left", new AbstractAction(){
         @Override
         public void actionPerformed(ActionEvent e) {
            ga.moveBlockLeft();
         }
     });
     am.put("up", new AbstractAction(){
         @Override
         public void actionPerformed(ActionEvent e) {
            ga.rotateBlock();
         }
     });
     am.put("down", new AbstractAction(){
         @Override
         public void actionPerformed(ActionEvent e) {
            ga.dropBlock();
         }
     });
     
    }
    


    public void startGame() {
        new GameThread(ga).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameAreaPlaceholder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gameAreaPlaceholder.setBackground(new java.awt.Color(204, 204, 204));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gameAreaPlaceholder;
    // End of variables declaration//GEN-END:variables
}
