/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;

import java.awt.Component;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author XingLu
 */
public class Game extends JFrame{
    
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 1000;
    
    public Game(){
        super("JUMPER");
        setSize(WIDTH,HEIGHT);

        Scene theGame = new Scene();
        ((Component)theGame).setFocusable(true);

        getContentPane().add(theGame);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main( String args[] ) {
	Game run = new Game();
    }
    
}
