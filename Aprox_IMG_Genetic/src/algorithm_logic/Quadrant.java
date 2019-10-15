/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_logic;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author javith
 */
public class Quadrant {

    private static int sizeXMax;
    private static int sizeYMax;

    private double posibility;
    private final int horizontalQuadrant;

    public int getHorizontalQuadrant() {
        return horizontalQuadrant;
    }

    public int getVerticalQuadrant() {
        return verticalQuadrant;
    }
    private final int verticalQuadrant;
    private ArrayList<Long> pixel_list;
    private Color color;

    public Quadrant(int pHorizontalQuadrant, int pVerticalQuadrant) {
        pixel_list = new ArrayList<>();

        this.posibility = 1;
        this.horizontalQuadrant = pHorizontalQuadrant;
        this.verticalQuadrant = pVerticalQuadrant;
    }

    public int getQuadrantXInLong(int pPos) {
        long result = pixel_list.get(pPos) >> 16;
        result = result & 32767;
        return (int) result;
    }

    public int getStartX() {
        return getEndX() - sizeXMax+1;

    }

    public int getEndX() {
        return (sizeXMax * horizontalQuadrant) + sizeXMax - 1;

    }

    public int getStartY() {
        return getEndY() - sizeYMax;

    }

    public int getEndY() {
        return sizeYMax * verticalQuadrant + sizeYMax - 1;

    }

    public int getQuadrantYInLong(int pPos) {
        long result = pixel_list.get(pPos);
        result = result & 32767;
        return (int) result;
    }

    public int getColorInLong(int pPos) {
        long valueOfColor = (pixel_list.get(pPos)) >> 32;
        color = new Color((int) valueOfColor);
        return color.getRGB();
    }

    public int getRedInLong(int pPos) {
        long valueOfColor = (pixel_list.get(pPos)) >> 32;
        color = new Color((int) valueOfColor);
        return color.getRed();
    }

    public int getGreenInLong(int pPos) {
        long valueOfColor = (pixel_list.get(pPos)) >> 32;
        color = new Color((int) valueOfColor);
        return color.getGreen();
    }

    public int getBlueInLong(int pPos) {
        long valueOfColor = (pixel_list.get(pPos)) >> 32;
        color = new Color((int) valueOfColor);
        return color.getBlue();
    }

    public void decrementposibility() {
        this.posibility -= 0.20;
    }

    public void increaseposibility() {
        if (posibility < 1) {
            this.posibility += 0.20;
        }
    }

    public static int getSizeXMax() {
        return sizeXMax;
    }

    public static void setSizeXMax(int sizeXMax) {
        Quadrant.sizeXMax = sizeXMax;
    }

    public static int getSizeYMax() {
        return sizeYMax;
    }

    public static void setSizeYMax(int sizeYMax) {
        Quadrant.sizeYMax = sizeYMax;
    }

    public double getPosibility() {
        return posibility;
    }

    public void setPosibility(double posibility) {
        this.posibility = posibility;
    }

    public ArrayList<Long> getPixel_list() {
        return pixel_list;
    }

    public void addPixel_list(long pPixel) {
        this.pixel_list.add(pPixel);
    }

}
