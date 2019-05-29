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
    private int ySpeed;
    private Image image;
    private boolean alive;
    private int activeDirection = (int)Math.round((Math.random()*2)-1);
    

    public User() {
        this(0, 0);
    }

    public User(int x, int y) {
        this(x, y, 0, 0);
    }

    public User(int x, int y, int xs, int ys) {
        this(x, y, 30, 30, xs, ys);
    }

    public User(int x, int y, int w, int h, int xs, int ys) {
        super(x, y, w, h);
        xSpeed = xs;
        ySpeed = ys;
        try {
            URL url = getClass().getResource("/images/wario.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {

        }
    }

    public void setSpeed(int xs, int ys){
        xSpeed = xs;
        ySpeed = ys;
    }
    
    public void setXSpeed(int xs) {
        xSpeed = xs;
    }

    public int getXSpeed() {
        return xSpeed;
    }
    
    public void setYSpeed(int ys) {
        ySpeed = ys;
    }

    public int getYSpeed() {
        return ySpeed;
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
                setY(getY() - getYSpeed());
                break;
            case "DOWN":
                setY(getY() + getYSpeed());
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
        return super.toString() + " " + getXSpeed() + " " + getYSpeed();
    }
}
