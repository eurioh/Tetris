package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class GameArea extends JPanel {
    
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;
    
    //call an object of TetrisBlock class
    private TetrisBlock block;
    
    
    //constructor - background to play game
    public GameArea(JPanel placeholder, int columns) {
        placeholder.setVisible(false);
        //get the position and size from JPanel placeholder (gameAreaPlaceHolder)
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width/gridColumns;
        gridRows = this.getBounds().height/gridCellSize;
        
        spawnBlock();
        //background = new Color[gridRows][gridColumns];
    }
    
    public void spawnBlock() {
        block = new TetrisBlock(new int[][]{ {1, 0}, {1, 0}, {1,1}}, Color.blue); 
        block.spawn(gridColumns);
    }

    //stop the block when reach bottom
    public boolean moveBlockDown() {
        if(checkBottom() == false)  return false; 
        
        block.moveDown();
        repaint();
        
        return true;
    }
    
    private boolean checkBottom(){
        
        if(block.getBottomEdge() == gridRows) {
            return false; 
        }
        return true;
    }    
    
    private void drawBlock(Graphics g) {
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();
        
        for(int row = 0; row < h; row++) {
            for(int col = 0; col < w; col++) {
                if(shape[row][col] == 1) {
                    
                    int x = (block.getX() + col) * gridCellSize ;
                    int y = (block.getY() + row) * gridCellSize; 
                    
                    g.setColor(c);
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    g.setColor(Color.black);
                    g.drawRect(x, y, gridCellSize, gridCellSize);
                }
            }
        }
    }
    
//    private void drawBackground(Graphics g) {
//        Color color; 
//        for(int r = 0; r < gridRows; r++) {
//            for(int c = 0; c <gridColumns; c++) {
//                color = background[r][c];
//                
//            }
//        }
//    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        //call paintComponent method from JPanel
        super.paintComponent(g);
        //draw a square
        for(int y = 0; y < gridRows; y++) {
            
            for(int x = 0; x < gridColumns; x++) {

                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
            }            
        }
        
        //drawBackground(g);
        drawBlock(g);
    }
}
