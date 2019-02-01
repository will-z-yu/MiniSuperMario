package com.example.android.minisupermario.controller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.android.minisupermario.model.HUD;

public class HUDController {
    /*Fields*/
    private HUD hud;
    private int screenWidth, screenHeight;
    private int buttonWidth, buttonHeight;


    /*Constructor*/
    public HUDController(int screenWidth, int screenHeight){
        hud = new HUD();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        buttonWidth = screenWidth/11;
        buttonHeight = screenWidth/11;
        setButtonPositions();
    }

    /*Methods*/
    //Set button positions
    private void setButtonPositions(){
        hud.setLeftButton(new Rect(0,screenHeight - buttonHeight, buttonWidth, screenHeight));

        hud.setRightButton(new Rect(buttonWidth, screenHeight - buttonHeight, buttonWidth * 2, screenHeight));

        hud.setAttackButton(new Rect(screenWidth - buttonWidth * 2, screenHeight - buttonHeight, screenWidth - buttonWidth, screenHeight));

        hud.setJumpButton(new Rect(screenWidth - buttonWidth, screenHeight - buttonHeight, screenWidth, screenHeight));
    }

    //Handle Input
    public void handleInput(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch(event.getAction() & MotionEvent.ACTION_MASK){
            //When button is pressed
            case MotionEvent.ACTION_DOWN:
                //Left
                if(hud.getLeftButton().contains(x,y)){
                    System.out.println("Clicked Left!");
                }
                //Right
                else if(hud.getRightButton().contains(x,y)){
                    System.out.println("Clicked Right!");
                }
                //Attack
                if(hud.getAttackButton().contains(x,y)){
                    System.out.println("Clicked Attack!");
                }
                //Jump
                else if(hud.getJumpButton().contains(x,y)){
                    System.out.println("Clicked Jump!");
                }
                break;

            //Release button
            case MotionEvent.ACTION_UP:
                System.out.println("Released Button!");

            default:
                System.out.println("Default Case for HUDController.handleInput()");
        }
    }

    //Draw HUD
    public void drawHUD(Canvas canvas, Bitmap[] hudBitmap){
        //Left
        canvas.drawBitmap(hudBitmap[0], null, hud.getLeftButton(), null);

        //Right
        canvas.drawBitmap(hudBitmap[1], null, hud.getRightButton(), null);

        //Attack
        canvas.drawBitmap(hudBitmap[2], null, hud.getAttackButton(), null);

        //Jump
        canvas.drawBitmap(hudBitmap[3], null, hud.getJumpButton(), null);
    }
}
