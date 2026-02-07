/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mazemaker;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mcdevitt_894713
 */
public class MapLayout {
    private int[][] layout;
    private boolean isStart;
    private boolean isFinish;
    private boolean isPortal;
    private Point startPos;
    private ArrayList<Rectangle> rects;
    private ArrayList<Rectangle> redWalls;
    private ArrayList<Rectangle> greenWalls;
    private ArrayList<Rectangle> blueWalls;
    private ArrayList<Rectangle> pinkWalls;
    private Color keyColor;
    private Rectangle key;
    private Rectangle finish;
    private Rectangle portal;
    private Point portalDest;
    private boolean hasCollectedKey = true;
    private int unitHeight;
    private int unitWidth;
    private int num;

    public MapLayout(int number, int height, int width, int sideHight, int sideWidth, String levelPack){
        unitHeight = height / sideHight;
        unitWidth = width / sideWidth;
        rects = new ArrayList<Rectangle>();
        redWalls = new ArrayList<Rectangle>();
        greenWalls = new ArrayList<Rectangle>();
        blueWalls = new ArrayList<Rectangle>();
        pinkWalls = new ArrayList<Rectangle>();
        layout = new int[sideHight][sideWidth];
        isStart = false;
        isFinish = false;
        isPortal = false;
        startPos = new Point(0, 0);
        num = number;
        Scanner fr = null;
        try {
            fr = new Scanner(new File(levelPack + "\\Screen" + num)); //file reader
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        //putting array from text file into array in class

        int currentSquare = 0;
        int currentX = 0;
        int currentY = 0;
        int chainCounter = 0;
        Point currentRectStart = new Point(0, 0);
        int extendAmount = 10;

        for (int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[i].length; j++){
                currentSquare = fr.nextInt();
                layout[i][j] = currentSquare;
                //System.out.print(layout[i][j] + " ");
                if(currentSquare == 1){
                    //rects.add(new Rectangle(j * unitWidth, ))
                    ///*
                    if(chainCounter == 0){
                        currentRectStart = new Point(j*unitWidth, i*unitHeight);
                    }
                    chainCounter++;
                    //*/
                } else {
                    Rectangle tempRect = new Rectangle(currentRectStart.x,currentRectStart.y, chainCounter * unitWidth, unitHeight);
                    if(i == (layout.length - 1)){
                        tempRect.setSize(tempRect.width, tempRect.height + extendAmount);
                    }
                    rects.add(tempRect);
                    chainCounter = 0;
                    if(currentSquare == 2)
                    {
                        isStart = true;
                        startPos = new Point((j * unitWidth) + (unitWidth / 2), (i * unitHeight) + (unitHeight / 2));
                    }
                    if(currentSquare == 3){
                        keyColor = Color.RED;
                        key = new Rectangle((j*unitWidth) + (unitWidth / 4), (i * unitHeight) + (unitHeight / 4), (unitWidth / 2), (unitHeight / 2));
                    }
                    if(currentSquare == 4){
                        redWalls.add(resizeEndDoor(i, j, extendAmount));
                    }
                    if(currentSquare == 5){
                        keyColor = Color.GREEN;
                        key = new Rectangle((j*unitWidth) + (unitWidth / 4), (i * unitHeight) + (unitHeight / 4), (unitWidth / 2), (unitHeight / 2));
                    }
                    if(currentSquare == 6){
                        greenWalls.add(resizeEndDoor(i, j, extendAmount));
                    }
                    if(currentSquare == 7){
                        keyColor = Color.BLUE;
                        key = new Rectangle((j*unitWidth) + (unitWidth / 4), (i * unitHeight) + (unitHeight / 4), (unitWidth / 2), (unitHeight / 2));
                    }
                    if(currentSquare == 8){
                        blueWalls.add(resizeEndDoor(i, j, extendAmount));
                    }
                    if(currentSquare == 9){//extra color key
                        keyColor = Color.PINK;
                        key = new Rectangle((j*unitWidth) + (unitWidth / 4), (i * unitHeight) + (unitHeight / 4), (unitWidth / 2), (unitHeight / 2));
                    }
                    if(currentSquare == 10){//extra color wall
                        pinkWalls.add(resizeEndDoor(i, j, extendAmount));
                    }
                    if(currentSquare == 11){
                        isFinish = true;
                        finish = new Rectangle((j*unitWidth) + (unitWidth / 4), (i * unitHeight) + (unitHeight / 4), (unitWidth / 2), (unitHeight / 2));
                    }
                    if(currentSquare == 12){
                        isPortal = true;
                        portal = new Rectangle((j*unitWidth) + ((unitWidth) / 4), (i * unitHeight) + ((3 * unitHeight) / 8), (unitWidth / 4), (unitHeight / 4));
                        portalDest = new Point((j*unitWidth) + ((3 * unitWidth) / 4), (i * unitHeight) + (unitHeight / 2));
                    }
                }
            }
            ///*
            if(chainCounter > 0){
                //System.out.println(currentRectStart + "" + chainCounter);
                Rectangle tempRect = new Rectangle(currentRectStart.x, currentRectStart.y, chainCounter * unitWidth, unitHeight);
                tempRect.setSize(tempRect.width + extendAmount, tempRect.height);
                if (i == (layout.length - 1)){
                    tempRect.setSize(tempRect.width, tempRect.height + extendAmount);
                }
                rects.add(tempRect);
            }
            chainCounter = 0;
            //*/
            //System.out.println();
        }
        //System.out.println(rects);
        //this.notifyAll();
        /*
        0 - nothing
        1 - normal wall
        2 - player start
        3 - red key
        4 - red wall
        5 - green key
        6 - green wall
        7 - blue key
        8 - blue wall
        9 - pink key
        10 - pink wall
        11 - finish
        12 - portal
        */
        System.out.println("initialized screen #" + number);
    }
    
    private Rectangle resizeEndDoor(int i, int j, int extendAmount){
        Rectangle r = new Rectangle(j * unitWidth, i * unitHeight, unitWidth, unitHeight);
        if (i == (layout.length - 1)) {
            r.setSize(r.width, r.height + extendAmount);
        }
        if (j == (layout[i].length - 1)) {
            r.setSize(r.width + extendAmount, r.height);
        }
        return r;
    }
    
    public ArrayList<Rectangle> getRects(){
        return rects;
    }

    public Point getStartLocation(){
        if(isStart == false){
            return null;
        }
        return startPos;
    }

    public boolean getIsStart(){
        return isStart;
    }

    public boolean getIsFinish(){
        return isFinish;
    }

    public int getNumber(){
        return num;
    }

    public ArrayList<Rectangle> getRedWalls(){
        return redWalls;
    }

    public ArrayList<Rectangle> getGreenWalls() {
        return greenWalls;
    }

    public ArrayList<Rectangle> getBlueWalls() {
        return blueWalls;
    }
    
    public ArrayList<Rectangle> getPinkWalls() {
        return pinkWalls;
    }

    public Color getKeyColor(){
        return keyColor;
    }

    public Rectangle getKeyRect(){
        return key;
    }

    public Rectangle getFinishRect(){
        return finish;
    }

    public boolean getIsPortal(){
        return isPortal;
    }

    public Rectangle getPortalRect(){
        return portal;
    }

    public Point getPortalDest(){
        return portalDest;
    }
}
