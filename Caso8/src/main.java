
import algorithm_logic.JPGManager;
import algorithm_logic.PixelManager;


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

    public static void main(String args[])  {
        
        
        PixelManager manager = new PixelManager("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Pikachu.jpg", 16);
        JPGManager plane = new JPGManager("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Plano.jpg");
        for (int cuadrante = 0; cuadrante < manager.getPixel_list().size(); cuadrante++) {

            plane.changePixel(manager.getX(cuadrante),manager.getY(cuadrante) ,manager.getColor(cuadrante));
            
        }
        plane.saveJPG("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Out.jpg");
    }
}
