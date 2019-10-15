/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author javith
 */
public class PixelManager {

    private final JPGManager jpgManager;
    private final ArrayList<Quadrant> quadrantList;

    public ArrayList<Quadrant> getQuadrantList() {
        return quadrantList;
    }

    public PixelManager(String pRouteOfJPG, double pSizeOfSections) {
        jpgManager = new JPGManager(pRouteOfJPG);
        quadrantList = new ArrayList<>();
        Quadrant.setSizeXMax(jpgManager.getWidth() / (int)(Math.pow(2, pSizeOfSections)));
        Quadrant.setSizeYMax(jpgManager.getHeight() / (int)(Math.pow(2, pSizeOfSections)));

        createQuadrants();
        createSelectionList(0.007);
        jpgManager.saveJPG("/home/javith/NetBeansProjects/Aprox_IMG_Genetic/src/Output.jpg");
    }

    private void createQuadrants() {
        int lastEndX = 0;
        int lastEndY = 0;
        int horizontalIndex = 0;
        int verticalIndex = 0;
        while (lastEndY < jpgManager.getHeight() - 1) {

            lastEndX += Quadrant.getSizeXMax();
            quadrantList.add(new Quadrant(horizontalIndex, verticalIndex));
            horizontalIndex++;
            if (lastEndX >= jpgManager.getWidth() - 1) {
                horizontalIndex = 0;
                lastEndX = 0;
                verticalIndex++;
                lastEndY += Quadrant.getSizeXMax();
            }
            System.out.println(quadrantList.get(quadrantList.size()-1).getStartX()+" "+quadrantList.get(quadrantList.size()-1).getEndX());
            
        }

    }

    private void savePixel(Color pColor, Quadrant pQuadrant) {
        long newSon = 0;
        newSon = (newSon | pColor.getRed()) << 8; //addcolorRed
        newSon = (newSon | pColor.getGreen()) << 8; //addcolorGreen
        newSon = (newSon | pColor.getBlue()) << 16; //addcolorBlue
        newSon = (newSon | pQuadrant.getHorizontalQuadrant()) << 16;
        newSon = (newSon | pQuadrant.getVerticalQuadrant());
        pQuadrant.addPixel_list(newSon);
    }

    private void createSelectionList(double pPercentage) {

        Random random = new Random();

        int randX;
        int randY;
        int sizeOfSample = (int) (jpgManager.getHeight() * jpgManager.getWidth() * pPercentage);
        int randQuadrant = 0;
        while (sizeOfSample > 0) {
            randQuadrant = (int)(Math.random()*quadrantList.size());

            double posibility = random.nextDouble();

            if (quadrantList.get(randQuadrant).getPosibility() > posibility) {
                int subX = quadrantList.get(randQuadrant).getEndX() - quadrantList.get(randQuadrant).getStartX();
                int minX = quadrantList.get(randQuadrant).getStartX();
                int subY = quadrantList.get(randQuadrant).getEndY() - quadrantList.get(randQuadrant).getStartY();
                int minY = quadrantList.get(randQuadrant).getStartY();
                randX = (int) ((random.nextDouble() * (subX + 1)) + minX);
                randY = (int) ((random.nextDouble() * (subY + 1)) + minY);
                System.err.println(subX + " " + minX);
                Color color = new Color(jpgManager.getPixel(randX, randY));

                if (color.getRGB() != -1) {
                    jpgManager.deletePixel(randX, randY);
                    savePixel(color, quadrantList.get(randQuadrant));
                    quadrantList.get(randQuadrant).increaseposibility();
                    sizeOfSample--;

                } else {
                    quadrantList.get(randQuadrant).decrementposibility();
                }
             

            }
               System.out.println(randQuadrant);

        }

    }

}
