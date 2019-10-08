/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author javith
 */
public class PixelManager {

    private JPGManager jpgManager;
    private JPGManager pruebas;
    private int sizeXMax;
    private int sizeYMax;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private ArrayList<Long> pixel_list;
    boolean flag1 = true;
    boolean flag2 = true;
    boolean flag3 = true;

    public PixelManager(String pRouteOfJPG, int SizeOfSections) {
        pixel_list = new ArrayList<>();
        createSetion(SizeOfSections, pRouteOfJPG);
        jpgManager.saveJPG("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Output.jpg");
    }

    public int getSizeXMax() {
        return sizeXMax;
    }

    public int getSizeYMax() {
        return sizeYMax;
    }

    public int getX(int pPos) {
        long result = pixel_list.get(pPos) >> 16;
        result = result & 32767;
        return (int) result;
    }

    public int getY(int pPos) {
        long result = pixel_list.get(pPos);
        result = result & 32767;
        return (int) result;
    }

    public int getColor(int pPos) {
        return (int) (pixel_list.get(pPos).longValue() >> 32);
    }

    public JPGManager getJpgManager() {
        return jpgManager;
    }

    public void setJpgManager(JPGManager jpgManager) {
        this.jpgManager = jpgManager;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public ArrayList<Long> getPixel_list() {
        return pixel_list;
    }

    public void setPixel_list(ArrayList<Long> pixel_list) {
        this.pixel_list = pixel_list;
    }

    private void createSelectionList(double pPercentage) {

        Random random = new Random();
        int fails = 0;
        int randX;
        int randY;

        int sizeOflist = pixel_list.size();
        while (sizeXMax * sizeYMax * pPercentage > (pixel_list.size() - sizeOflist) && fails != 120) {
            long newSon = 0;
            randX = random.nextInt(sizeXMax) + startX;
            randY = random.nextInt(sizeYMax) + startY;
            int newColorOfPixel = jpgManager.getPixel(randX, randY);
            if (randX < endX && randY < endY) {
                if (newColorOfPixel != -1) {
                    newSon = (newSon | newColorOfPixel) << 16;
                    newSon = (newSon | randX) << 16;
                    newSon = (newSon | randY);
                    pixel_list.add(newSon);
                    jpgManager.deletePixel(randX, randY);
                    fails = 0;

                } else {
                    fails++;
                }
            }
        }

    }
    //pSizeOfSections ^2 

    private void createSetion(int pSizeOfSections, String pRouteJPG) {
        pruebas = new JPGManager("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Plano.jpg");
        double porcentageOfSeleccion = 0.1;
        jpgManager = new JPGManager(pRouteJPG);

        sizeXMax = jpgManager.getWidth() / pSizeOfSections;
        sizeYMax = jpgManager.getHeight() / pSizeOfSections;
        this.startX = 0;
        this.endX = sizeXMax;
        this.startY = 0;
        this.endY = sizeYMax;

        while (startY < jpgManager.getHeight()) {
            while (startX < jpgManager.getWidth()) {
                createSelectionList(porcentageOfSeleccion);
                startX = endX;
                endX += sizeXMax;
            }
            startX = 0;
            endX = sizeXMax;
            startY = endY;
            endY += sizeYMax;
        }

    }
}
