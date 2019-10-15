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
public class Genetic {

    public ArrayList<Long> getPoblation() {
        return poblation;
    }

    public void setPoblation(ArrayList<Long> poblation) {
        this.poblation = poblation;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public ArrayList<Long> getAuxList() {
        return auxList;
    }

    public void setAuxList(ArrayList<Long> auxList) {
        this.auxList = auxList;
    }
    // 32 color 16 X 16 Y
    private ArrayList<Long> poblation;
    private Random rand = new Random();
    public ArrayList<Long> auxList;

    public Genetic(ArrayList<Long> plobation) {
        this.poblation = plobation;
    }

    public int getX(int pPos) {
        long result = poblation.get(pPos) >> 16;
        result = result & 32767;
        return (int) result;
    }

    public int getY(int pPos) {
        long result = poblation.get(pPos);
        result = result & 32767;
        return (int) result;
    }

    public int getColor(int pPos) {
        return (int) (poblation.get(pPos) >> 32);
    }
    private mixBytes(int )
    private int newcolor(Color colorFather, Color colorMother) {
        int newRed=0;
        int newGreen=0;
        int newBlue =0;
        return 
    }

    private long reproduction(long father, long mother) {
        Color colorFather = new Color((int) (father >> 32));
        Color colorMother = new Color((int) (mother >> 32));

        long son = 0;

        return son;
    }

    public void reproductions() {
        auxList = new ArrayList<>();
        for (int i = 0; i < poblation.size(); i++) {
            poblation.add(reproduction(poblation.get(rand.nextInt(poblation.size())), poblation.get(rand.nextInt(poblation.size()))));
        }
    }

}
