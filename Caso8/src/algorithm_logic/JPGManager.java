/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author javith
 */
public class JPGManager {

    boolean flag1 = true;
     boolean flag2 = true;
    private BufferedImage img = null;
    private File f = null;
    private int width;
    private int height;

    public JPGManager(String pruta_img) {
        openJPG(pruta_img);
    }

    private void openJPG(String pRoute) {
        try {
            f = new File(pRoute);
            img = ImageIO.read(f);
            width = img.getWidth();
            height = img.getHeight();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void deletePixel(int x, int y) {
//35586080
//-338402
        img.setRGB(x, y, -1);
    }

    public void changePixel(int x, int y, int color) {
        
        img.setRGB(x, y, color);
    }

    public void saveJPG(String pRoute) {
        try {
            f = new File(pRoute);
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public int getPixel(int pX, int pY) {
        int value = img.getRGB(pX, pY);
        
        return value;
    }
}
