/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;

import java.awt.Color;

/**
 *
 * @author XingLu
 */
public abstract class Block implements Locatable{
    private int xPos, yPos, width, height;
    private Color color;
    
    public Block(){
        this(0, 0);
    }
    
    public Block(int x, int y){
        this(x, y, 10, 10);
    }
    
    public Block(int x, int y, int w, int h){
        this(x, y, w, h, Color.BLACK);
    }
    
    public Block(int x, int y, int w, int h, Color c){
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        color = c;
    }

    /**
     * @return the xPos
     */
    public int getX() {
        return xPos;
    }

    /**
     * @param xPos the xPos to set
     */
    public void setX(int x) {
        this.xPos = x;
    }

    /**
     * @return the yPos
     */
    public int getY() {
        return yPos;
    }

    /**
     * @param yPos the yPos to set
     */
    public void setY(int y) {
        this.yPos = y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    public Color getColor(){
        return color;
    }
    
    public void setColor(Color c){
        color = c;
    }
}
