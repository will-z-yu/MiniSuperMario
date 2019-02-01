package com.example.android.minisupermario.controller;

import com.example.android.minisupermario.model.Player;

public class PlayerController {
    /*Fields*/
    final static int maxMoveCount = 5;
    private Player player;
    private int moveLeftCount, moveRightCount;

    /*Constructor*/
    public PlayerController(Player player){
        this.player = player;
    }

    /*Methods*/
    //Initialize Mario states
    public void initializeMario(int xPosPixel, int yPosPixel){
        if(!player.reset()){
            player.setxPosPixel(xPosPixel);
            player.setyPosPixel(yPosPixel);
            player.setReset(true);
        }
    }

    public void idle(){

    }

    public void moveLeft(){

    }

    public void moveRight(){

    }

    public void attack(){

    }

    public void jump(){

    }
}
