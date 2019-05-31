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
public class User extends MovingThing{
    private int xSpeed;
    private int jSpeed;
    private int fSpeed;
    private Image image;
    private boolean alive;
    private int activeDirection = (int)Math.round((Math.random()*2)-1);
    

    public User() {
        this(0, 0);
    }

    public User(int x, int y) {
        this(x, y, 0, 0);
    }

    public User(int x, int y, int xs, int js) {
        this(x, y, 30, 30, xs, js, 5);
    }

    public User(int x, int y, int w, int h, int xs, int js, int fs) {
        super(x, y, w, h);
        xSpeed = xs;
        jSpeed = js;
        fSpeed = fs;
        try {
            URL url = getClass().getResource("/images/mario.png");
            image = ImageIO.read(url);
        } catch (Exception e) {

        }
    }

    public void setSpeed(int xs, int ys){
        xSpeed = xs;
        jSpeed = ys;
    }
    
    public void setXSpeed(int xs) {
        xSpeed = xs;
    }

    public int getXSpeed() {
        return xSpeed;
    }
    
    public void setJSpeed(int ys) {
        jSpeed = ys;
    }

    public int getJSpeed() {
        return jSpeed;
    }
    
    public void setFSpeed(int fs) {
        fSpeed = fs;
    }
    
    public int getFSpeed(){
        return fSpeed;
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
            case "UP":
                setY(getY() - getJSpeed());
                break;
            case "DOWN":
                setY(getY() + getFSpeed());
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
        return super.toString() + " " + getXSpeed() + " " + getJSpeed();
    }
}
