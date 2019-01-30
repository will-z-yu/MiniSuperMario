package com.example.android.minisupermario.controller;

import com.example.android.minisupermario.model.Player;

public class PlayerController {
    /*Fields*/
    final static int maxMoveCount = 5;
    Player player;
    private int moveLeftCount, moveRightCount;

    /*Constructor*/
    public PlayerController(Player player){
        this.player = player;
    }

    /*Methods*/
    public void idle(){

    }

    public void moveLeft(int pixel){
        player.faceLeft();

        //Moving on display
        if(player.getxPosPixel() - pixel > 0){
            player.moveLeftPixel(pixel);
            moveLeftCount++;
            if(moveRightCount != 0)
                moveRightCount--;
        }
        else {
            System.out.println("Sorry you can't go further/ Level complete!");
        }

        //Moving on level map
        /*After moving certain times, player advances to the next grid
        * moveRightCount is maxMoveCount - 1 because if player move right after advancing on the level map,
        * player goes back to previous xPos*/
        if(moveLeftCount == maxMoveCount){
            moveLeftCount = 0;
            moveRightCount = maxMoveCount - 1;
            player.moveLeft();
        }
    }

    public void moveRight(int pixel){

    }

    public void attack(){

    }

    public void jump(){

    }

}
