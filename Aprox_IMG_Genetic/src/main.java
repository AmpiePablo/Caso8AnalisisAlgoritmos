
import algorithm_logic.JPGManager;
import algorithm_logic.PixelManager;
import algorithm_logic.Quadrant;
import static java.lang.Math.random;
import java.util.Random;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author javith
 */
public class main {

    public static void main(String args[]) {
        Random random = new Random();

        PixelManager manager = new PixelManager("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Pikachu.jpg", 5);
        JPGManager plane = new JPGManager("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Plano.jpg");

        for (Quadrant q : manager.getQuadrantList()) {
            for (long pixel : q.getPixel_list()) {
                System.out.println(q.getStartX() + "," + q.getEndX());
                int randX = (int) ((random.nextDouble() * (q.getEndX() - q.getStartX() + 1)) + q.getStartX());
                int randY = (int) ((random.nextDouble() * (q.getEndY() - q.getStartY() + 1)) + q.getStartY());

                plane.changePixel(randX, randY, (int) (pixel >> 32));
            }
        }
        plane.saveJPG("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Out.jpg");

    }
}
