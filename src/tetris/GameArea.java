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
        
        //color for blocks turns into background
        background = new Color[gridRows][gridColumns];
    }
    
    public void spawnBlock() {
        block = new TetrisBlock(new int[][]{ {1, 0}, {1, 0}, {1,1}}, Color.blue); 
        block.spawn(gridColumns);
    }

    //stop the block when reach bottom
    public boolean moveBlockDown() {
        if(checkBottom() == false){
            moveBlockToBackground();
            clearLines();
            return false;
        } 
        
        block.moveDown();
        repaint();
        
        return true;
    }
    
    private boolean checkBottom(){
        
        if(block.getBottomEdge() == gridRows) {
            return false; 
        }
        
        //stackup
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int col = 0; col < w; col++) {
            for (int row = h-1; row >= 0; row--) {   
                if(shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if(y < 0) break;
                    if(background[y][x] != null) return false; 
                    break;
                } 
            }
        }
        
        return true;
    }    
    
    private boolean checkLeft() {
        if(block.getLeftEdge() == 0) return false;
        
        //block stop moving left
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {   
                if(shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if(y < 0) break;
                    if(background[y][x] != null) return false; 
                    break;
                } 
            }
        }
        
        return true;
    }
    
    private boolean checkRight() {
        if(block.getRightEdge() == gridColumns) return false;
        
        //block stop moving right
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int row = 0; row < h; row++) {
            for (int col = w-1; col >= 0; col--) {   
                if(shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if(y < 0) break;
                    if(background[y][x] != null) return false; 
                    break;
                } 
            }
        }
        
        return true;
    }
    
    public void moveBlockRight() {
        if(!checkRight()) return;
        block.moveRight();
        repaint();
    }
    
    public void moveBlockLeft() {
        if(!checkLeft()) return;
        block.moveLeft();
        repaint();
    }    
    
    public void dropBlock() {
        while (checkBottom()) {
            block.moveDown();
        }
        repaint();     
    }
    
    public void rotateBlock() {
        block.rotate();
        repaint();
    }
    
    public void clearLines() {
        boolean lineFilled;
        
        for (int r = gridRows-1; r >= 0 ; r--) {
            lineFilled = true;
            for (int c = 0; c < gridColumns; c++) {
                if(background[r][c] == null) {
                    lineFilled = false;
                    break;
                }                
            }  
            if(lineFilled) {
                clearLine(r);
                shiftDown(r);
                clearLine(0);
                r++;
                repaint();
            }
        }
    }
    
    public void clearLine(int r) {
        for (int i = 0; i < gridColumns; i++) {
                    background[r][i] = null;
                }
    }
    
    public void shiftDown(int r) {
        for(int row = r; row > 0; row--) {
            for(int col = 0; col < gridColumns; col++) {
                background[row][col] = background[row-1][col];
            }
            
        }
    }
    
    private void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        
        Color color = block.getColor();
        
        for(int r = 0; r < h; r++) {
            for(int c = 0; c < w; c++) {
                if(shape[r][c] == 1) {
                    background[r+yPos][c+xPos] = color;
                }
            }
        }
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
                    
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
    
    private void drawBackground(Graphics g) {
        Color color; 
        for(int r = 0; r < gridRows; r++) {
            for(int c = 0; c <gridColumns; c++) {
                color = background[r][c];
                
                if(color != null) {
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y) {
        //draw single square of a grid 
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    
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
        
        //drawing the background of the content of the background color array
        drawBackground(g);
        
        drawBlock(g);
    }
}
