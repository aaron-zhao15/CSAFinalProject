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
public class Ground extends Block {
    private static String[] boundaries = {"TOP",
                                    "LEFT", "RIGHT",
                                        "BOTTOM"};
    private String boundary;
    
    public Ground(){
        super();
    }
    
    public Ground(int x, int y){
        super(x, y);
    }
    
    public Ground(int x, int y, int w, int h){
        super(x, y, w, h);
    }
    
    public Ground(int x, int y, int w, int h, Color c){
        super(x, y, w, h, c);
    }
    
    public Ground(int x, int y, int w, int h, Color c, int bound){
        super(x, y, w, h, c);
        boundary = boundaries[bound];
    }
    
    public void setPos(int x, int y){
        super.setX(x);
        super.setY(y);
    }
}
