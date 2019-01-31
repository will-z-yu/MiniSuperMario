package com.example.android.minisupermario.model;

public class Player {
    /*Fields*/
    private final static int LEFT = 1;
    private final static int RIGHT = 2;

    //States
    private boolean reset;
    private boolean isAlive;
    private boolean isMoving;
    private boolean isAttacking;
    private boolean isJumping;
    private boolean isRed;
    private boolean isSuper;

    //Attributes
    private int lives;
    private int direction;
    private int sprite;


    private int xPos;
    private int yPos;
    private int xPosPixel;
    private int yPosPixel;

    private int Points;
    private int count;
    private boolean Next;

    /*Constructor*/
    public Player(int xPos, int yPos){
        reset = false;

        isAlive = true;
        isMoving = false;
        isAttacking = false;
        isJumping = false;
        isSuper = false;
        isRed = false;


        lives = 3;
        direction = LEFT;
        sprite = 0;


        this.xPos = xPos;
        this.yPos = yPos;
    }

    /*Methods*/

    //Get
    public boolean reset(){ return this.reset; }

    public boolean isAlive(){ return this.isAlive; }

    public boolean isMoving(){ return this.isMoving; }

    public boolean isAttacking(){ return this.isAttacking; }

    public boolean isJumping(){ return this.isJumping;}

    public boolean isRed(){ return this.isRed; }

    public boolean isSuper(){ return this.isSuper; }

    public int getLives(){ return this.lives; }

    public int getDirection(){ return this.direction; }

    public int getSprite(){ return this.sprite; }

    public int getXPos(){ return this.xPos; }

    public int getYPos(){ return this.yPos; }

    public int getxPosPixel(){ return this.xPosPixel; }

    public int getyPosPixel(){ return this.yPosPixel; }

    //Set
    public void setReset(boolean set){ reset = set;}

    //States
    public void dead(){ isAlive = false; }
    public void alive(){ isAlive = true; }

    public void moving(){ isMoving = true;}
    public void stopMoving(){ isMoving = false;}

    public void attacking(){ isAttacking = true;}
    public void stopAttacking(){ isAttacking = false;}

    public void jumping(){ isJumping = true;}
    public void stopJumping(){ isJumping = false;}

    public void pickupFlower(){ isRed = true; }
    public void redStateEnded(){ isRed = false;}

    public void pickupMushroom(){ isSuper = true; }
    public void superStateEnded(){ isSuper = false;}

    public void faceLeft(){ direction = LEFT;}
    public void faceRight(){ direction = RIGHT;}

    public void setSprite(int sprite){ this.sprite = sprite;}

    //Move
    public void moveLeft(){ xPos--; }
    public void moveRight(){ xPos++; }

    public void moveLeftPixel(int pixels){ xPosPixel -= pixels; }
    public void moveRightPixel(int pixels){ xPosPixel += pixels; }

    public void setxPosPixel(int x){ xPosPixel = x; }
    public void setyPosPixel(int y){ yPosPixel = y; }

    //Jump/Fall
    public void moveUpPixel(int pixels){ yPosPixel -= pixels; }
    public void moveDownPixel(int pixels){ yPosPixel += pixels; }
}
