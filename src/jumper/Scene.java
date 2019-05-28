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
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Scene extends Canvas implements KeyListener, Runnable {
    
    private boolean[] keys;
    
    
    
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'h') {
            keys[0] = true;
        }
        if (e.getKeyChar() == 'j') {
            keys[1] = true;
        }
        if (e.getKeyChar() == 'u') {
            keys[2] = true;
        }
        if (e.getKeyChar() == 'n') {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'h') {
            keys[0] = false;
        }
        if (e.getKeyChar() == 'j') {
            keys[1] = false;
        }
        if (e.getKeyChar() == 'u') {
            keys[2] = false;
        }
        if (e.getKeyChar() == 'n') {
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
