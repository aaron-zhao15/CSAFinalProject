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
    private ArrayList<Enemy> enemies;
//    private Enemy enemy1;
//    private Enemy enemy2;
    
    public boolean win;
    
    private ArrayList<Ground> grounds;
    
    private File data;
    
    private String name;
    
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
    private Image loseScreen;
    private Image winScreen;
    
    private boolean[] keys;
    private BufferedImage back;
    
    public Scene(){
        setBackground(Color.black);

        name = "";
        
        keys = new boolean[5];
        
        accel = 10;
        timeElapsed = 0;
        timeStart = -1;
        score = 0;
        lives = 3;
        
        win = false;
        
        startXPos = 640;
        startYPos = 700;
        
        user = new User(startXPos, startYPos, 50, 50, 5, 10, 10);
        
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(300, 350, 50, 50, 3, "/images/wario.png"));
        enemies.add(new Enemy(700, 350, 50, 50, 3, "/images/waluigi.png"));
        
//        enemy1 = new Enemy(300, 350, 50, 50, 3, "/images/wario.png");
//        enemy2 = new Enemy(700, 350, 50, 50, 3, "/images/waluigi.png");
        
        
        grounds = new ArrayList<Ground>();
        
        grounds.add(new Ground(0, 800, 1280, 200, Color.GREEN));
        grounds.add(new Ground(200, 400, 200, 20, Color.GREEN));
        grounds.add(new Ground(600, 400, 200, 20, Color.GREEN));
        
        try {
            URL url = getClass().getResource("/images/background.jpg");
            backgroundImg = ImageIO.read(url);
            URL url2 = getClass().getResource("/images/gameover.png");
            loseScreen = ImageIO.read(url2);
            URL url3 = getClass().getResource("/images/winscreen.jpg");
            winScreen = ImageIO.read(url3);
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
        graphToBack.drawString("User: " + name, SIZE, SIZE);
        
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
        
        if(enemies.size() > 0){
            enemies.get(0).move("RIGHT");
            if(enemies.get(0).getX() <= ground1.getX()
                    || enemies.get(0).getX()+enemies.get(0).getWidth() >= ground1.getX()+ground1.getWidth()){
                enemies.get(0).setXSpeed(-enemies.get(0).getXSpeed());
            }
        }
        
        if(enemies.size() > 1){
            Ground ground2 = grounds.get(2);
            enemies.get(1).move("RIGHT");
            if(enemies.get(1).getX() <= ground2.getX()
                    || enemies.get(1).getX()+enemies.get(1).getWidth() >= ground2.getX()+ground2.getWidth()){
                enemies.get(1).setXSpeed(-enemies.get(1).getXSpeed());
            }
        }
        
        
        
        if(!keys[2]){
            user.move("DOWN");
        }
        
        if (keys[0]) {
            user.move("LEFT");
        }
        if (keys[1]) {
            user.move("RIGHT");
        }
        if (keys[2]) {
            if(timeStart == -1){
                timeStart = timeElapsed;
            }
            user.move("UP");
            if(timeElapsed > timeStart + 50){
                keys[2] = false;
                timeStart = -2;
            }
        }
        if (keys[3]) {
            user.move("DOWN");
        }
        
        if(timeStart == -2){
            timeStart = -1;
        }
        
        for(Enemy e : enemies){
            checkEnemy(e);
            checkPlayer(e);
            
        }
        
        
        if(!user.getAlive()){
            try{
                Thread.sleep(500);
            }catch(Exception e){
                
            }
            if(lives > 0){
                lives--;
                user.setAlive(true);
                user.setPos(startXPos, startYPos);
            }
        }
        
        if(lives == 0){
            graphToBack.clearRect(0, 0, 1280, 1000);
            //enemies.clear();
            user.setAlive(false);
            backgroundImg = loseScreen;
            graphToBack.drawImage(backgroundImg, 0, 0, null);
        }
        
        for(int i = 0; i < enemies.size(); i++){
            if(!enemies.get(i).getAlive()){
                enemies.remove(i);
            }
        }

        if(!enemies.isEmpty()){
            for(Enemy e: enemies){
                e.draw(graphToBack);
            }
        }
        else if (enemies.isEmpty()){
            graphToBack.clearRect(0, 0, 1280, 1000);
            //enemies.clear();
            //user.setAlive(false);
            backgroundImg = winScreen;
            graphToBack.drawImage(backgroundImg, 0, 0, null);
        }
        
        if(user.getAlive()){
            user.draw(graphToBack);
        }
        
        twoDGraph.drawImage(back, null, 0, 0);
    }
    
    
    public void checkEnemy(Enemy enemy){
        if(user.getX() <= enemy.getX() + enemy.getWidth() && user.getX() >= enemy.getX()
                || user.getX() + user.getWidth() >= enemy.getX() && user.getX() + user.getWidth() <= enemy.getX() + enemy.getWidth()){
            if(user.getY() + user.getHeight() <= enemy.getY() + 5 && user.getY() + user.getHeight() >= enemy.getY() - 5
                    && user.isFalling()){
                enemy.setAlive(false);
                score += 100;
            }
        }
        else {
        }
    }
    
    public void checkPlayer(Enemy enemy){
        if(user.getX() <= enemy.getX() + enemy.getWidth() && user.getX() >= enemy.getX()
                || user.getX() + user.getWidth() >= enemy.getX() && user.getX() + user.getWidth() <= enemy.getX() + enemy.getWidth()){
            if(user.getY() <= enemy.getY() + enemy.getHeight()
                    && user.getY() >= enemy.getY()){
                user.setAlive(false);
                score = 0;
            }
        }
        else {
        }
    }
    
    public void setName(String n){
        name = n;
    }
    public String getName(){
        return name;
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
