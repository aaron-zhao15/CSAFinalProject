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
public class UserTest {
    public static void main( String args[] )
    {
        MovingThing test = new User();
        System.out.println("User 1 " + test);

        User test2 = new User(50, 75);
        System.out.println("User 2 " + test2);

        User test3 = new User(7, 7, 6, 5, 1, 1, 5);
        test3.setX(3);
        test3.setY(5);
        System.out.println("User 3 " + test2);
        
        MovingThing test4 = new Enemy();
        System.out.println("Enemy 1 " + test4);
        
        Enemy test5 = new Enemy(50, 75);
        System.out.println("Enemy 2 " + test5);
        
        Enemy test6 = new Enemy(7, 7, 6, 5, 1);
        System.out.println("Enemy 3 " + test6);
    }
}
