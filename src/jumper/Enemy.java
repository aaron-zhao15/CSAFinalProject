/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author XingLu
 */
public class Enemy extends MovingThing {
    
    private int xSpeed;
    private Image image;
    private boolean alive;
    private int activeDirection = (int)Math.round((Math.random()*2)-1);
    

    public Enemy() {
        this(0, 0, 30, 30, 0);
    }

    public Enemy(int x, int y) {
        this(x, y, 30, 30, 0);
    }

    public Enemy(int x, int y, int s) {
        this(x, y, 30, 30, s);
    }
    
    public Enemy(int x, int y, int w, int h){
        this(x, y, w, h, 0);
    }

    public Enemy(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        xSpeed = s;
        try {
            URL url = getClass().getResource("/images/wario.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {

        }
    }

    public void setXSpeed(int xs) {
        xSpeed = xs;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void move(String direction) {
        String temp = direction.toUpperCase();
        switch(temp){
            case "LEFT":
                setX(getX() - getXSpeed());
                break;
            case "RIGHT":
                setX(getX() + getXSpeed());
                break;
        }
    }

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    public boolean getAlive(){
        return alive;
    }
    
    public void setAlive(boolean a){
        alive = a;
    }
    
    public int getActiveDirection() {
        return activeDirection;
    }

    public void setActiveDirection(int activeDirection) {
        this.activeDirection = activeDirection;
    }

    public String toString() {
        return super.toString() + " " + getXSpeed();
    }
    
}
