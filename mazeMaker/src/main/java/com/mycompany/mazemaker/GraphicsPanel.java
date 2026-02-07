/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mazemaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author mcdevitt_894713
 */
public class GraphicsPanel extends JPanel implements Runnable{
    private Rectangle playerRect;
    private Rectangle currentPlayerMoveRect;
    private Rectangle checkX;
    private Rectangle checkY;
    private int width;
    private int height;
    private int velXLeft;
    private int velXRight;
    private int velYUp;
    private int velYDown;
    private int speed;
    private int sizeBuffer;
    private double heightMult;
    private double widthMult;
    private boolean running;
    private boolean finished;
    private boolean hardMode;
    private boolean restarting;
    private boolean jumpscare;
    private MapLayout[][] screen;
    private int screenX;
    private int screenY;
    private int startScreenX;
    private int startScreenY;
    private ArrayList<Color> collectedKeys;
    private double currentTime;
    private double bestTime;
    private BufferedImage lockImg;
    private BufferedImage sklumbo;
    private BufferedImage key;
    private BufferedImage outOfBoundsGuy;
    private File deathSound;
    private File victorySound;
    private File keySound;
    private File outOfBoundsSound;
    private String levelPack;

    GraphicsPanel(){
        width = 400;//this.getWidth();
        height = 300;//this.getHeight();
        velXLeft = 0;
        velXRight = 0;
        velYUp = 0;
        velYDown = 0;
        speed = 4;
        running = false;
        finished = false;
        hardMode = false;
        restarting = false;
        jumpscare = false;
        heightMult = 1.0;
        widthMult = 1.0;
        sizeBuffer = 1;
        bestTime = 0.0;
        levelPack = "levels/defaultLevel";
        /*
        screen = new MapLayout[3][3];
        int screenNum = 1;
        for(int y = 0; y < screen.length; y++){
            for(int x = 0; x < screen[y].length; x++){
                screen[y][x] = new MapLayout(screenNum, height, width, 7, 6);
                if (screen[y][x].getIsStart()){
                    startScreenX = x;
                    startScreenY = y;
                }
                screenNum++;
            }
        }
        screenX = startScreenX;
        screenY = startScreenY;
        */
        try{
            lockImg = ImageIO.read(new File("requiredImages/lockImage.png"));
            sklumbo = ImageIO.read(new File("requiredImages/sklumbo.png"));
            key = ImageIO.read(new File("requiredImages/key.png"));
            outOfBoundsGuy = ImageIO.read(new File("requiredImages/outOfBoundsGuy.png"));
            deathSound = new File("requiredImages/deathNoise.wav");
            victorySound = new File("requiredImages/victoryNoise.wav");
            keySound = new File("requiredImages/keyNoise.wav");
            outOfBoundsSound = new File("requiredImages/outOfBoundsNoise.wav");
        } catch (Exception e) {

        }
        System.out.println("screen width is: " + width + ", screen height is:" + height);
    }

    public void resetLoop(){
        collectedKeys = new ArrayList<>();
        screenX = startScreenX;
        screenY = startScreenY;

        playerRect = new Rectangle((width / 2) - 5 + 50, (height / 2) - 5, 10, 10);
        playerRect.setLocation(currentScreen().getStartLocation());
        currentPlayerMoveRect = playerRect.getBounds();
        checkX = playerRect.getBounds();
        checkY = playerRect.getBounds();
        restarting = true;
    }

    private void startLoop(){
        screen = new MapLayout[3][3];
        int screenNum = 1;
        for(int y = 0; y < screen.length; y++){
            for(int x = 0; x < screen[y].length; x++){
                screen[y][x] = new MapLayout(screenNum, height, width, 7, 6, levelPack);
                if (screen[y][x].getIsStart()){
                    startScreenX = x;
                    startScreenY = y;
                }
                screenNum++;
            }
        }
        try {
            Scanner scanner = new Scanner(new File(levelPack + "/highScore"));
            bestTime = scanner.nextDouble();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphicsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        screenX = startScreenX;
        screenY = startScreenY;
    	running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    private MapLayout currentScreen(){
        return screen[screenY][screenX];
    }

    public void updatePlayer(){
        //System.out.println("updatingPlayer");
        currentPlayerMoveRect.setLocation(playerRect.getLocation());
        checkX.setLocation(playerRect.getLocation());
        checkY.setLocation(playerRect.getLocation());
        if (new Rectangle(0,0, width, height).contains(playerRect.getLocation())){
            //System.out.println("moved check rectangle");
            //check for walls
            //currentPlayerMoveRect.translate(velX, 0);
            //currentPlayerMoveRect.setLocation(playerRect.getLocation());
            checkX.translate(velXRight + velXLeft, 0);
            checkY.translate(0, velYUp + velYDown);
            boolean touchingWallX = playerTouchingWall(checkX);
            boolean touchingWallY = playerTouchingWall(checkY);
            if(!touchingWallX){
            	currentPlayerMoveRect.setLocation(checkX.x, currentPlayerMoveRect.y);
            }
            if(!touchingWallY){
            	currentPlayerMoveRect.setLocation(currentPlayerMoveRect.x, checkY.y);
            }
            Rectangle checkRect2 = new Rectangle(playerRect);
            checkRect2.translate(velXLeft + velXRight, velYUp + velYDown);
            if (!playerTouchingWall(checkRect2)){
            	currentPlayerMoveRect.setLocation(checkRect2.getLocation());
            }
            playerRect.setLocation(currentPlayerMoveRect.getLocation());
            if (hardMode && (touchingWallY || touchingWallX)){
                playSound(0);
		resetLoop();
            }

            /*if (!touchingWall) { //check for walls here
                playerRect.setLocation(currentPlayerMoveRect.getLocation());
            } /*else { //For hard mode
                running = false;
                startLoop();
            }*/
            if (!((currentScreen().getKeyRect() == null) || (collectedKeys.contains(currentScreen().getKeyColor())))){
                if (playerRect.intersects(currentScreen().getKeyRect())) {
                    playSound(2);
                    collectedKeys.add(currentScreen().getKeyColor());
                }
            }
            if((currentScreen().getIsFinish()) && (playerRect.intersects(currentScreen().getFinishRect()))){
                playSound(1);
                if (bestTime != 0.0) {
                    if (currentTime < bestTime) {
                        bestTime = currentTime;
                    }
                } else {
                    bestTime = currentTime;
                }
                if((bestTime >= currentTime) || (bestTime == 0.0)){
                    try {
                        File file = new File(levelPack + "/highScore");
                        file.delete();
                        file = new File(levelPack + "/highScore");
                        file.createNewFile();
                        FileWriter fw = new FileWriter(file);
                        fw.write("" + bestTime);
                        fw.close();
                        System.out.println("updated High Score " + bestTime);
                    } catch (Exception e){

                    }
                }
                finished = true;
                running = false;
            }
            if((currentScreen().getIsPortal()) && (playerRect.intersects(currentScreen().getPortalRect()))){
                usePortal();
            }
        }
        else {
            playerScreenShift();
        }
    }

    private boolean playerTouchingWall(Rectangle checkR){
        ArrayList<Rectangle> walls = currentScreen().getRects();
        for (Rectangle r : walls){
            if(checkR.intersects(r)){
                return true;
            }
        }
        if (!collectedKeys.contains(Color.RED)){
            ArrayList<Rectangle> redWalls = currentScreen().getRedWalls();
            for (Rectangle r : redWalls) {
                if (checkR.intersects(r)) {
                    return true;
                }
            }
        }
        if (!collectedKeys.contains(Color.GREEN)){
            ArrayList<Rectangle> greenWalls = currentScreen().getGreenWalls();
            for (Rectangle r : greenWalls) {
                if (checkR.intersects(r)) {
                    return true;
                }
            }
        }
        if (!collectedKeys.contains(Color.BLUE)){
            ArrayList<Rectangle> blueWalls = currentScreen().getBlueWalls();
            for (Rectangle r : blueWalls) {
                if (checkR.intersects(r)) {
                    return true;
                }
            }
        }
        if (!collectedKeys.contains(Color.PINK)){
            ArrayList<Rectangle> pinkWalls = currentScreen().getPinkWalls();
            for (Rectangle r : pinkWalls) {
                if (checkR.intersects(r)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void usePortal(){
        for(int y = 0; y < screen.length; y++){
            for(int x = 0; x < screen[y].length; x++){
                if (screen[y][x].getIsPortal()){
                    if (screen[y][x].equals(currentScreen())){
                        continue;
                    }
                    screenX = x;
                    screenY = y;
                    playerRect.setLocation(screen[y][x].getPortalDest());
                    //updatePlayer();
                    System.out.println("" + y + " " + x);
                    return;
                }
            }
        }
    }

    private void playSound(int sndInt){
        File sound = null;
        switch(sndInt){
            case 0 -> sound = deathSound;
            case 1 -> sound = victorySound;
            case 2 -> sound = keySound;
            case 3 -> sound = outOfBoundsSound;
        }
        if(!sound.exists()){
            return;
        }
        //System.out.println(sound.getName());
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(GraphicsPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GraphicsPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(GraphicsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void playerScreenShift(){
        int currentSide = new Rectangle(0, 0, width, height).outcode(currentPlayerMoveRect.getLocation());
        //bottom side
        if(playerRect.getY() >= height){
            screenY++;
            if (screenY > 2){
                startScare();
                return;
            }
            playerRect.setLocation((int)playerRect.getX(), 10);
        }
        //left side
        if(playerRect.getX() <= 0){
            screenX--;
            if (screenX < 0){
                startScare();
                return;
            }
            playerRect.setLocation(width - 20, (int)playerRect.getY());
        }
        //right side
        if(playerRect.getX() >= width){
            screenX++;
            if (screenX > 2){
                startScare();
                return;
            }
            playerRect.setLocation(10, (int)playerRect.getY());
        }
        //top side
        if(playerRect.getY() <= 0){
            screenY--;
            if (screenY < 0){
                startScare();
                return;
            }
            playerRect.setLocation((int)playerRect.getX(), height - 20);
        }
        System.out.println("currentOutcode: " + currentSide);
        System.out.println("now in screen " + currentScreen().getNumber());
    }

    private void startScare(){
        jumpscare = true;
        running = false;
        playSound(3);
        System.out.println("jumpscare");
    }

    /*
    public void setVelX(int x){
        //velX = x;
    }

    public void setVelY(int y){
        //velY = y;
    }
    */

    public int getState(){
        if (running){
            return 1;
        } else if(jumpscare){
            return 2;
        } else if (!finished){
            return 3;
        } else {
            return 0;
        }
    }
    public void inputDown(java.awt.event.KeyEvent evt){
	/*
	*if is on main menu, start thread
	*if is in game, reset thread
	*if is finished, set to main menu and update graphics
        *if is jumpscare, set to main menu and update graphics
	*/
        if(evt.getKeyCode() == evt.VK_SPACE){
            if (running){ //when in game
            	resetLoop();
            } else if(jumpscare){
                running = false;
                jumpscare = false;
                finished = false;
                updateGraphics();
            } else if(!finished){ //on main menu
                running = false;
                startLoop();
                resetLoop();
            } else { //on end screen
                finished = false;
                running = false;
                jumpscare = false;
                updateGraphics();
            }
        }

        if(evt.getKeyCode() == evt.VK_H){
            hardMode = !hardMode;
        }

        if(evt.getKeyCode() == evt.VK_ESCAPE){
            running = false;
        }
        if((evt.getKeyCode() == evt.VK_W)||(evt.getKeyCode() == evt.VK_UP)){
            velYUp = -speed;
            //System.out.println("" + velX + " " + velY);
        }
        if((evt.getKeyCode() == evt.VK_A)||(evt.getKeyCode() == evt.VK_LEFT)){
            velXLeft = -speed;
            //System.out.println("" + velX + " " + velY);
        }
        if((evt.getKeyCode() == evt.VK_S)||(evt.getKeyCode() == evt.VK_DOWN)){
            velYDown = speed;
            //System.out.println("" + velX + " " + velY);
        }
        if((evt.getKeyCode() == evt.VK_D)||(evt.getKeyCode() == evt.VK_RIGHT)){
            velXRight = speed;
            //System.out.println("" + velX + " " + velY);
        }

    }

    public void inputUp(java.awt.event.KeyEvent evt){
        if((evt.getKeyCode() == evt.VK_W)||(evt.getKeyCode() == evt.VK_UP)){
            velYUp = 0;
            return;
            //System.out.println("" + velX + " " + velY);
        }
        if((evt.getKeyCode() == evt.VK_S)||(evt.getKeyCode() == evt.VK_DOWN)){
            velYDown = 0;
            return;
        }
        if((evt.getKeyCode() == evt.VK_A)||(evt.getKeyCode() == evt.VK_LEFT)){
            velXLeft = 0;
            return;
            //System.out.println("" + velX + " " + velY);
        }
        if((evt.getKeyCode() == evt.VK_D)||(evt.getKeyCode() == evt.VK_RIGHT)){
            velXRight = 0;
            return;
        }
    }

    public void updateGraphics(){
        //playerRect.translate(velX, velY);
        repaint();
    }

    public void setLevelDir(String path){
    	levelPack = path;
    }
    
    public boolean isOnMainMenu(){
        return (!running) && (!jumpscare) && (!finished);
    }
    
    public boolean isHardMode(){
        return hardMode;
    }

    private Rectangle scaleRect(Rectangle r, Graphics g){
        Rectangle tempR = new Rectangle((int)(r.x * widthMult), (int)(r.y * heightMult), (int)(r.width * widthMult) + sizeBuffer, (int)(r.height * heightMult) + sizeBuffer);
        g.fillRect(tempR.x, tempR.y, tempR.width, tempR.height);
        return tempR;
    }

    private void scaleText(String text, float sizeMult, int posX, int posY, Graphics g, Font defaultFont, double avgScale){
    	float fontSizeMult = sizeMult * (float)avgScale;
        g.setFont(defaultFont.deriveFont(defaultFont.getSize() * fontSizeMult));
        int bufferX = g.getFontMetrics().stringWidth(text) / 2;
        g.drawString(text, (int)(posX * widthMult) - bufferX, (int)(posY * heightMult));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        heightMult = this.getHeight() / (double) height;
        widthMult = this.getWidth() / (double) width;
        double avgScale = (heightMult + widthMult) / 2;
        Font defaultFont = g.getFont();
        Rectangle scaledRect = null;
        if(running){
            g.setColor(Color.YELLOW);
            ArrayList<Rectangle> rects = currentScreen().getRects();
            for (Rectangle r : rects) {
                scaleRect(r, g);
            }
            g.setColor(Color.RED);
            if (!collectedKeys.contains(Color.RED)) {
                ArrayList<Rectangle> redWalls = currentScreen().getRedWalls();
                for (Rectangle r : redWalls) {
                    scaledRect = scaleRect(r, g);
                    if(lockImg != null){
                        g.drawImage(lockImg, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
                    }
                }
                if (currentScreen().getKeyColor() == Color.RED){
                    Rectangle redKey = currentScreen().getKeyRect();
                    scaledRect = scaleRect(redKey, g);
                    g.drawImage(key, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
                }
            }
            g.setColor(Color.GREEN);
            if (!collectedKeys.contains(Color.GREEN)) {
                ArrayList<Rectangle> greenWalls = currentScreen().getGreenWalls();
                for (Rectangle r : greenWalls) {
                    scaledRect = scaleRect(r, g);
                    if(lockImg != null){
                        g.drawImage(lockImg, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
                    }
                }
                if (currentScreen().getKeyColor() == Color.GREEN){
                    Rectangle greenKey = currentScreen().getKeyRect();
                    scaledRect = scaleRect(greenKey, g);
                    g.drawImage(key, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
                }
            }

            g.setColor(Color.BLUE);
            if (!collectedKeys.contains(Color.BLUE)) {
                ArrayList<Rectangle> blueWalls = currentScreen().getBlueWalls();
                for (Rectangle r : blueWalls) {
                    scaledRect = scaleRect(r, g);
                    if(lockImg != null){
                        g.drawImage(lockImg, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
                    }
                }
                if (currentScreen().getKeyColor() == Color.BLUE){
                    Rectangle blueKey = currentScreen().getKeyRect();
                    scaledRect = scaleRect(blueKey, g);
                    g.drawImage(key, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
                }
            }
            
            g.setColor(Color.PINK);
            if (!collectedKeys.contains(Color.PINK)) {
                ArrayList<Rectangle> pinkWalls = currentScreen().getPinkWalls();
                for (Rectangle r : pinkWalls) {
                    scaledRect = scaleRect(r, g);
                    if(lockImg != null){
                        g.drawImage(lockImg, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
                    }
                }
                if (currentScreen().getKeyColor() == Color.PINK){
                    Rectangle pinkKey = currentScreen().getKeyRect();
                    scaledRect = scaleRect(pinkKey, g);
                    g.drawImage(key, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
                }
            }

            g.setColor(Color.CYAN);
            if(currentScreen().getIsPortal()){
                scaledRect = scaleRect(currentScreen().getPortalRect(), g);
            }


            g.setColor(Color.LIGHT_GRAY);
            if(currentScreen().getIsFinish()){
                scaleRect(currentScreen().getFinishRect(), g);
            }

            if (sklumbo != null){
                scaledRect = scaleRect(playerRect, g);
                g.drawImage(sklumbo, scaledRect.x, scaledRect.y, scaledRect.width, scaledRect.height, null);
            } else {
                scaleRect(playerRect, g);
            }


            g.setColor(Color.BLACK);
            String timer = "" + (currentTime / 1_000_000_000);
            //float timerSizeMult = (float) avgScale;
            //g.setFont(defaultFont.deriveFont(defaultFont.getSize() * timerSizeMult));
            int timerX = g.getFontMetrics().getHeight();
            g.drawString(timer, 0, timerX);
            //draw map
            //vertical lines
            g.drawLine((int)(360 * widthMult), (int)(5 * heightMult), (int)(360 * widthMult), (int)(35 * heightMult));
            g.drawLine((int)(370 * widthMult), (int)(5 * heightMult), (int)(370 * widthMult), (int)(35 * heightMult));
            g.drawLine((int)(380 * widthMult), (int)(5 * heightMult), (int)(380 * widthMult), (int)(35 * heightMult));
            g.drawLine((int)(390 * widthMult), (int)(5 * heightMult), (int)(390 * widthMult), (int)(35 * heightMult));
            //horizontal lines
            g.drawLine((int)(360 * widthMult), (int)(5 * heightMult), (int)(390 * widthMult), (int)(5 * heightMult));
            g.drawLine((int)(360 * widthMult), (int)(15 * heightMult), (int)(390 * widthMult), (int)(15 * heightMult));
            g.drawLine((int)(360 * widthMult), (int)(25 * heightMult), (int)(390 * widthMult), (int)(25 * heightMult));
            g.drawLine((int)(360 * widthMult), (int)(35 * heightMult), (int)(390 * widthMult), (int)(35 * heightMult));
            //player marker
            scaleRect(new Rectangle(360 + (screenX * 10), 5 + (screenY * 10), 10, 10), g);

        } else if(finished) {
            String finishTitle = "";
            if (hardMode){
        	finishTitle = "Wow! Amazing! Spectacular!";
            } else {
        	finishTitle = "Congratulations!";
            }

            scaleText(finishTitle, 2.0f, 200, 100, g, defaultFont, avgScale);
            /*
            float finishTitleFontSizeMult = 2.0f * (float) avgScale;
            g.setFont(defaultFont.deriveFont(defaultFont.getSize() * finishTitleFontSizeMult));
            int finishTitleX = g.getFontMetrics().stringWidth(finishTitle) / 2;
            g.drawString(finishTitle, (int)(200 * widthMult) - finishTitleX, (int)(100 * heightMult));
            */

            String finishSubtext = "You have completed the maze";
            if (hardMode) {
                finishSubtext += " on hard dificulty!";
            } else {
                finishSubtext += "!";
            }
            scaleText(finishSubtext, 1.5f, 200, 150, g, defaultFont, avgScale);
            /*
            float finishSubtextFontSizeMult = 1.5f * (float) avgScale;
            g.setFont(defaultFont.deriveFont(defaultFont.getSize() * finishSubtextFontSizeMult));
            int finishSubtextX = g.getFontMetrics().stringWidth(finishSubtext) / 2;
            g.drawString(finishSubtext, (int)(200 * widthMult) - finishSubtextX, (int)(150 * heightMult));
            */

            String timeDisplay = "Your time is: " + (currentTime / 1_000_000_000) + " seconds!";
            g.drawString(timeDisplay, (int)(200 * widthMult) - (g.getFontMetrics().stringWidth(timeDisplay) / 2), (int)(150 * heightMult) + (g.getFontMetrics().getHeight()));

            

            String bestDisplay = "Your best time is: " + (bestTime / 1_000_000_000) + " seconds!";
            g.drawString(bestDisplay, (int) (200 * widthMult) - (g.getFontMetrics().stringWidth(bestDisplay) / 2), (int) (175 * heightMult) + (g.getFontMetrics().getHeight()));


            String finishInstructions = "Press space to return to menu";
            scaleText(finishInstructions, 1.0f, 200, 250, g, defaultFont, avgScale);
            /*
            float finishInstructionsFontSizeMult = (float)avgScale;
            g.setFont(defaultFont.deriveFont(defaultFont.getSize() * finishInstructionsFontSizeMult));
            int finishInstructionsX = g.getFontMetrics().stringWidth(finishInstructions) / 2;
            g.drawString(finishInstructions, (int)(200 * widthMult) - finishInstructionsX, (int)(250f * heightMult));
            */
        } else if(jumpscare){
            g.setColor(Color.BLACK);
            g.drawImage(outOfBoundsGuy, 0, 0, this.getWidth(), this.getHeight(), null);
            scaleText("Congrats...", 2.0f, 200, 25, g, defaultFont, avgScale);
            scaleText("You got out of bounds...", 1.5f, 200, 75, g, defaultFont, avgScale);
            scaleText("Might want to think about fixing that...", 1.5f, 200, 200, g, defaultFont, avgScale);
            scaleText("Well... Press space to return to the menu.", 1.75f, 200, 250, g, defaultFont, avgScale);
        } else {
            String title = "MazeMaker";
            scaleText(title, 2.5f, 200, 100, g, defaultFont, avgScale);
            /*
            float titleFontSizeMult = 2.0f * (float)avgScale;
            g.setFont(defaultFont.deriveFont(defaultFont.getSize() * titleFontSizeMult));
            int titleX = g.getFontMetrics().stringWidth(title) / 2;
            g.drawString(title, (int)(200 * widthMult) - titleX, (int)(100 * heightMult));
            */

            String hardInst = "Press H for hard mode";
            scaleText(hardInst, 1.5f, 200, 150, g, defaultFont, avgScale);

            String subtext = "Press space to start";
            scaleText(subtext, 1.5f, 200, 175, g, defaultFont, avgScale);
            
            scaleText("Press M to choose a Level Pack", 1.5f, 200, 200, g, defaultFont, avgScale);
            
            scaleText("Press C to open the Level Creator", 1.5f, 200, 225, g, defaultFont, avgScale);
            
            /*
            float subtextFontSizeMult = (float) avgScale;
            g.setFont(defaultFont.deriveFont(defaultFont.getSize() * subtextFontSizeMult));
            int subtextX = g.getFontMetrics().stringWidth(subtext) / 2;
            g.drawString(subtext, (int)(200 * widthMult) - subtextX, (int)(200f * heightMult));
            */
            String instructions = "Use arrow keys or WASD to move";
            scaleText(instructions, 1.25f, 200, 250, g, defaultFont, avgScale);

            String inst2 = "Use space to reset";
            scaleText(inst2, 1.25f, 200, 270, g, defaultFont, avgScale);

            String inst3 = "Use esc to return to menu";
            scaleText(inst3, 1.25f, 200, 290, g, defaultFont, avgScale);
        }

        g.setFont(defaultFont);

    }

    @Override
    public void run() {
        System.out.println("runningloop");
        long lastLoopTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        long startTime = lastLoopTime;
        int frames = 0;
        while(running){
            //System.out.println("looping");
            long now = System.nanoTime();
            if (restarting){
            	startTime = now;
            	restarting = false;
            }
            currentTime = now - startTime;
            //System.out.println(currentTime / 1_000_000_000);
            delta += (now - lastLoopTime) / ns;
            lastLoopTime = now;
            while (delta >= 1) {
                //System.out.println("" + velX + velY);
                if(!((velXLeft == 0) && (velXRight == 0) && (velYUp == 0) && (velYDown == 0))){
                    //System.out.println("running updatePlayer");
                    updatePlayer();
                }
                delta--;
            }
            //System.out.println("currentX : " + playerRect.getX() + "currentPlayerMoveX : " + currentPlayerMoveRect.getX());
            updateGraphics();
            ///*

            //*/
            frames++;
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
       	//Thread.currentThread().interrupt();

    }
}
