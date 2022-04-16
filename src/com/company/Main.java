package com.company;
import model.*;

import java.awt.*;

import static java.awt.Color.*;

public class Main {

    public static void main(String[] args) {

        Grid testGrid = new Grid();
        Tetrominoe aaa = new SShape();
        testGrid = aaa.placePiece(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveDown(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveSide(testGrid,1);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveSide(testGrid,-1);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.rotate(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveDown(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveSide(testGrid,1);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveSide(testGrid,-1);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.rotate(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveDown(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveSide(testGrid,1);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveSide(testGrid,-1);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.rotate(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveDown(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveSide(testGrid,1);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.moveSide(testGrid,-1);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
        testGrid = aaa.rotate(testGrid);
        testGrid.displayInConsole();
        System.out.println(aaa.getPosition().getY()+" "+aaa.getPosition().getX());
    }

}
