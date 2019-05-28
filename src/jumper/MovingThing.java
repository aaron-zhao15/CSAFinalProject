/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;

import java.awt.Graphics;

/**
 *
 * @author XingLu
 */
public abstract class MovingThing implements Moveable{
    //initialize variables
    private int xPos, yPos, width, height, xSpd, ySpd;
    
    public MovingThing(){
        this(200, 200);
    }
    
    public MovingThing(int x, int y){
        this(x, y, 100, 100);
    }
    
    public MovingThing(int x, int y, int w, int h){
        this(x, y, w, h, 0, 0);
    }
    
    public MovingThing(int x, int y, int w, int h, int xs, int ys){
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        xSpd = xs;
        ySpd = ys;
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
    public void setX(int xPos) {
        this.xPos = xPos;
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
    public void setY(int yPos) {
        this.yPos = yPos;
    }
    
    public void setPos(int x, int y){
        xPos = x;
        yPos = y;
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

    /**
     * @return the xSpd
     */
    public int getxSpd() {
        return xSpd;
    }

    /**
     * @param xSpd the xSpd to set
     */
    public void setxSpd(int xSpd) {
        this.xSpd = xSpd;
    }

    /**
     * @return the ySpd
     */
    public int getySpd() {
        return ySpd;
    }

    /**
     * @param ySpd the ySpd to set
     */
    public void setySpd(int ySpd) {
        this.ySpd = ySpd;
    }
    
    public abstract void move(String direction);
    
    public abstract void draw(Graphics window);
    
    public String toString() {
        return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
    }
    
}
