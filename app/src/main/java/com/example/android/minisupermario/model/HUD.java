package com.example.android.minisupermario.model;

import android.graphics.Rect;

public class HUD {
    /*Fields*/
    private Rect leftButton;
    private Rect rightButton;
    private Rect attackButton;
    private Rect jumpButton;

    /*Constructor*/
    public HUD(){
        leftButton = new Rect();
        rightButton = new Rect();
        attackButton = new Rect();
        jumpButton = new Rect();
    }

    /*Methods*/
    //Get
    public Rect getLeftButton(){ return leftButton; }

    public Rect getRightButton(){ return rightButton; }

    public Rect getAttackButton(){return attackButton; }

    public Rect getJumpButton(){ return jumpButton; }

    //Set
    public void setLeftButton(Rect leftButton){ this.leftButton = leftButton; }

    public void setRightButton(Rect rightButton){ this.rightButton = rightButton; }

    public void setAttackButton(Rect attackButton) { this.attackButton = attackButton; }

    public void setJumpButton(Rect jumpButton){ this.jumpButton = jumpButton; }
}
