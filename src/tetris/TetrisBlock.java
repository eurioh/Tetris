package tetris;

import java.awt.Color;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x, y; 
    
    //constructor
    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }
    
    public void spawn(int gridWidth) {
        y = -1 -getHeight();
        x = (gridWidth - getWidth())/2;
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
    public int getBottomEdge() { return y + getHeight();}
    
}
