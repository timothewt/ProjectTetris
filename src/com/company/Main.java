package com.company;
import model.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Grid testGrid = new Grid();
        Tetrominoe aaa = new ZShape();
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
