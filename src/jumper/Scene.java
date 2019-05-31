/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;

/**
 *
 * @author XingLu
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Scene extends Canvas implements KeyListener, Runnable {
    
    private User user;
    private Enemy enemy1;
    private ArrayList<Ground> grounds;
    
    private File data;
    
    private int startXPos;
    private int startYPos;
    
    private int score;
    private int accel;
    private int lives;
    
    private int posStart;
    private int posOffset;
    
    private int timeStart;
    private int timeElapsed;
    
    private Image backgroundImg;
    
    private boolean[] keys;
    private BufferedImage back;
    
    public Scene(){
        setBackground(Color.black);

        keys = new boolean[5];
        
        accel = 10;
        timeElapsed = 0;
        timeStart = -1;
        score = 0;
        lives = 3;
        
        startXPos = 640;
        startYPos = 700;
        
        user = new User(300, 400, 50, 50, 10, 10, 10);
        enemy1 = new Enemy(300, 350, 50, 50, 3);
        
        posStart = enemy1.getX();
        posOffset = 0;
        
        grounds = new ArrayList<Ground>();
        
        grounds.add(new Ground(0, 800, 1280, 200, Color.GREEN));
        grounds.add(new Ground(200, 400, 200, 10, Color.GREEN));
        grounds.add(new Ground(600, 400, 200, 10, Color.GREEN));
        
        try {
            URL url = getClass().getResource("/images/background.jpg");
            backgroundImg = ImageIO.read(url);
        } catch (Exception e) {
        }
        
        data = new File("newfile");
        
        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
        
    }
    
    public void update(Graphics window) {
        paint(window);
    }
    
    public void paint(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D) window;
        
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }
        
        Graphics graphToBack = back.createGraphics();

        graphToBack.drawImage(backgroundImg, 0, 0, null);
        
        
        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("Jumper ", 25, 50);
        
        graphToBack.setColor(Color.WHITE);
        graphToBack.drawString("Score " + score, 300, 50);
        
        graphToBack.setColor(Color.RED);
        graphToBack.drawString("Lives " + lives, 500, 50);
        
        for(Ground ground : grounds){
            graphToBack.setColor(ground.getColor());
            graphToBack.fillRect(ground.getX(), ground.getY(), ground.getWidth(), ground.getHeight());
        }
        
        
        for(Ground ground : grounds){
            if((user.getY()+user.getHeight() <= ground.getY() + 10) 
                        && (user.getY()+user.getHeight() >= ground.getY())
                        && (user.getX()+user.getWidth() >= ground.getX())
                        && (user.getX() <= ground.getX() + ground.getWidth())){
                user.setY(ground.getY()-user.getHeight());
            }
            

//            if(!((user.getY()+user.getHeight() <= ground.getY() + 10) 
//                        && (user.getY()+user.getHeight() >= ground.getY())
//                        && (user.getX()+user.getWidth() >= user.getX())
//                        && (user.getX() <= ground.getX() + ground.getWidth()))
//                    && !keys[2]){
//                user.move("DOWN");
//            }
        }
        
        timeElapsed++;
        
        Ground ground1 = grounds.get(1);
        enemy1.move("RIGHT");
        if(enemy1.getX() <= ground1.getX()
                || enemy1.getX()+enemy1.getWidth() >= ground1.getX()+ground1.getWidth()){
            enemy1.setXSpeed(-enemy1.getXSpeed());
        }
        
        
        
        if(!keys[2]){
            user.move("DOWN");
        }
        
        if (keys[0] == true) {
            user.move("LEFT");
        }
        if (keys[1] == true) {
            user.move("RIGHT");
        }
        if (keys[2] == true) {
            if(timeStart == -1){
                timeStart = timeElapsed;
            }
            user.move("UP");
            if(timeElapsed > timeStart + 50){
                keys[2] = false;
                timeStart = -2;
            }
        }
        if (keys[3] == true) {
            user.move("DOWN");
        }
        
        if(timeStart == -2){
            timeStart = -1;
        }
        
        user.draw(graphToBack);
        enemy1.draw(graphToBack);
        
        twoDGraph.drawImage(back, null, 0, 0);
    }
    
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
            keys[0] = true;
        }
        if (e.getKeyChar() == 'd') {
            keys[1] = true;
        }
        if (e.getKeyChar() == 'w') {
            keys[2] = true;
        }
        if (e.getKeyChar() == 's') {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
            keys[0] = false;
        }
        if (e.getKeyChar() == 'd') {
            keys[1] = false;
        }
        if (e.getKeyChar() == 'w') {
            keys[2] = false;
        }
        if (e.getKeyChar() == 's') {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
    
}
