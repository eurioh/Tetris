package tetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x, y; 
    private int[][][] shapes;
    private int currentRotation; 
    
    private Color[] availableColors = {Color.green, Color.red, Color.blue};
    //constructor
    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        
        
        initShapes();
    }
    
    //for block rotation
    private void initShapes() {
        shapes = new int[4][][]; 
        
        for(int i = 0; i < 4; i++) {
            int r = shape[0].length;
            int c = shape.length;
            
            shapes[i] = new int[r][c];
            for(int y = 0; y < r; y++) {
                for(int x =0; x < c; x++) {
                shapes[i][y][x] = shape[c-x-1][y];
                }
            }
            shape = shapes[i];
        }
    }
    
    public void spawn(int gridWidth) {
        Random r = new Random();
        
        currentRotation = r.nextInt(shapes.length);
        shape = shapes[currentRotation];
        
        //rotation happen before sending to x, y cooridnance of the block
        //height and width of the block depend on the shape of the block
        y = -getHeight();
        x = r.nextInt(gridWidth - getWidth());
        
        color = availableColors[r.nextInt(availableColors.length)];
   
    }
    
    public int[][] getShape(){return shape;}
    public Color getColor() {return color;}
    public int getHeight() {return shape.length;} //height of the block
    public int getWidth() {return shape[0].length;}//width of the block
    public int getX() {return x;}
    public int getY() {return y;}
    
    public void moveDown() { y++; }
    public void moveLeft() { x--; }
    public void moveRight() { x++; }
    public void rotate() {
        currentRotation++;
        if(currentRotation > 3) currentRotation = 0;
        shape = shapes[currentRotation];
    }
    public int getBottomEdge() { return y + getHeight();}
    
    public int getLeftEdge() { return x;}
    public int getRightEdge() { return x + getWidth();}
}
