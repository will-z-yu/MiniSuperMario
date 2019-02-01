package com.example.android.minisupermario.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.android.minisupermario.R;
import com.example.android.minisupermario.controller.HUDController;
import com.example.android.minisupermario.controller.PlayerController;
import com.example.android.minisupermario.model.Player;

import static android.view.MotionEvent.ACTION_DOWN;

public class GameView extends SurfaceView implements Runnable{
    /*Fields*/
    //Threads
    Thread gameThread = null;
    private volatile boolean gameRunning;

    //Used for threads
    private SurfaceHolder holder;
    private Canvas canvas;

    //"Resolution" of the game
    private final static int vertnum = 6;
    private final static int horinum = 10;
    private final static int maxMoveCount = 5;//change in PlayerController too
    private int screenWidth;
    private int screenHeight;
    private int rowHeight;
    private int columnWidth;

    //Bitmaps
    private Bitmap background[];
    private Bitmap foreground[];
    private Bitmap hudBitmap[];
    private Bitmap objects[];
    private Bitmap playerSprite[];

    //For drawing
    private Rect rect;
    private Paint paint;

    //FPS calculation
    long fps;
    private long timeThisFrame;

    //Player
    private Player mario;
    private PlayerController player1;

    //HUD
    private HUDController hudController;

    /*Constructor*/
    public GameView (Context context, int screenWidth, int screenHeight) {
        super(context);
        setFocusable(true);
        setWillNotDraw(false);

        //Set resolution
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        columnWidth = screenWidth/horinum;
        rowHeight = screenHeight/vertnum;

        //Instantiate
        background = new Bitmap[3];
        foreground = new Bitmap[2];
        hudBitmap = new Bitmap[4];
        objects = new Bitmap[3];
        playerSprite = new Bitmap[48];

        initializeBitmaps();

        //For Drawing
        holder = getHolder();
        rect = new Rect();
        paint = new Paint();

        //Game Objects
        mario = new Player(29,4);
        player1 = new PlayerController(mario);

        hudController = new HUDController(screenWidth, screenHeight);
    }

    /*Methods*/
    @Override
    public void run(){
        while(gameRunning){
            //Record start frame to calculate fps
            long startFrameTime = System.currentTimeMillis();

            update();

            draw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        hudController.handleInput(event);

        invalidate();
        return true;
    }

    //Stop gameThread to save CPU memory
    public void pause(){
        gameRunning = false;
        try{
            gameThread.join();
        } catch(InterruptedException e){
            Log.e("ERROR:", "joining thread");
        }
    }

    //Start gameThread again onResume
    public void resume(){
        gameRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /*Private Methods*/
    //Checks game logic/mechanic
    private void update(){

    }

    //Draws on screen
    private void draw(){
        //Make sure surface is valid
        if (holder.getSurface().isValid()) {
            //Lock the canvas for drawing
            canvas = holder.lockCanvas();

            //Draw here
            //Set Mario's initial (x,y)
            player1.initializeMario((horinum - 1) * columnWidth,(vertnum - 2) * rowHeight);

            //Background
            drawBackground(canvas);

            //Player
            drawPlayer(canvas, mario);

            //Display FPS
            paint.setColor(Color.argb(255,  249, 129, 0));
            paint.setTextSize(45);
            canvas.drawText("FPS:" + fps, 20, 40, paint);

            //HUD
            hudController.drawHUD(canvas, hudBitmap);

            //Unlock canvas
            holder.unlockCanvasAndPost(canvas);
        }
    }

    //Draw background
    private void drawBackground(Canvas canvas){
        rect.set(0, 0, screenWidth, screenHeight);
        canvas.drawBitmap(background[0], null, rect, null);
    }

    //Draw Player
    private void drawPlayer(Canvas canvas, Player player){
        int x = player.getxPosPixel();
        int y = player.getyPosPixel();
        rect.set(x, y, x + columnWidth, y + rowHeight);
        canvas.drawBitmap(playerSprite[player.getSprite()], null, rect, null);
    }

    //Assigns Bitmap objects to Bitmap[]
    private void initializeBitmaps(){
        //Background
        background[0] = makeBitmap(R.drawable.background_default);
        background[1] = makeBitmap(R.drawable.background_victory);
        background[2] = makeBitmap(R.drawable.background_game_over);

        //Foreground
        foreground[0] = makeBitmap(R.drawable.stage_clear);
        foreground[1] = makeBitmap(R.drawable.game_over);

        //Buttons
        hudBitmap[0] = makeBitmap(R.drawable.button_left);
        hudBitmap[1] = makeBitmap(R.drawable.button_right);
        hudBitmap[2] = makeBitmap(R.drawable.button_a);
        hudBitmap[3] = makeBitmap(R.drawable.button_b);

        //Objects
        objects[0] = makeBitmap(R.drawable.object_brick);
        objects[1] = makeBitmap(R.drawable.object_flower);
        objects[2] = makeBitmap(R.drawable.object_mushroom);


        /*Player*/
        /*----------------------------------------------------------------------------------------*/
        //Idle
        playerSprite[0] = makeBitmap(R.drawable.player_idle_left);
        playerSprite[1] = makeBitmap(R.drawable.player_idle_right);

        //Move
        playerSprite[2] = makeBitmap(R.drawable.player_walk_left_1);
        playerSprite[3] = makeBitmap(R.drawable.player_walk_left_2);
        playerSprite[4] = makeBitmap(R.drawable.player_walk_left_3);

        playerSprite[5] = makeBitmap(R.drawable.player_walk_right_1);
        playerSprite[6] = makeBitmap(R.drawable.player_walk_right_2);
        playerSprite[7] = makeBitmap(R.drawable.player_walk_right_3);

        //Jump
        playerSprite[8] = makeBitmap(R.drawable.player_jump_left);
        playerSprite[9] = makeBitmap(R.drawable.player_jump_right);

        //Attack
        playerSprite[10] = makeBitmap(R.drawable.player_attack_left_1);
        playerSprite[11] = makeBitmap(R.drawable.player_attack_left_2);

        playerSprite[12] = makeBitmap(R.drawable.player_attack_right_1);
        playerSprite[13] = makeBitmap(R.drawable.player_attack_right_2);

        //Attacked
        playerSprite[14] = makeBitmap(R.drawable.player_attacked_left);
        playerSprite[15] = makeBitmap(R.drawable.player_attacked_right);

        /*----------------------------------------------------------------------------------------*/

        /*Red Player*/
        /*----------------------------------------------------------------------------------------*/
        //Idle
        playerSprite[16] = makeBitmap(R.drawable.red_player_idle_left);
        playerSprite[17] = makeBitmap(R.drawable.red_player_idle_right);

        //Move
        playerSprite[18] = makeBitmap(R.drawable.red_player_walk_left_1);
        playerSprite[19] = makeBitmap(R.drawable.red_player_walk_left_2);
        playerSprite[20] = makeBitmap(R.drawable.red_player_walk_left_3);

        playerSprite[21] = makeBitmap(R.drawable.red_player_walk_right_1);
        playerSprite[22] = makeBitmap(R.drawable.red_player_walk_right_2);
        playerSprite[23] = makeBitmap(R.drawable.red_player_walk_right_3);

        //Attack
        playerSprite[24] = makeBitmap(R.drawable.red_player_attack_left_1);
        playerSprite[25] = makeBitmap(R.drawable.red_player_attack_left_2);

        playerSprite[26] = makeBitmap(R.drawable.red_player_attack_right_1);
        playerSprite[27] = makeBitmap(R.drawable.red_player_attack_right_2);

        //Jump
        playerSprite[28] = makeBitmap(R.drawable.red_player_jump_left);
        playerSprite[29] = makeBitmap(R.drawable.red_player_jump_right);

        //Attacked
        playerSprite[30] = makeBitmap(R.drawable.red_player_attacked_left);
        playerSprite[31] = makeBitmap(R.drawable.red_player_attacked_right);

        /*----------------------------------------------------------------------------------------*/

        /*Super Player*/
        /*----------------------------------------------------------------------------------------*/
        //Idle
        playerSprite[32] = makeBitmap(R.drawable.super_player_idle_left);
        playerSprite[33] = makeBitmap(R.drawable.super_player_idle_right);

        //Move
        playerSprite[34] = makeBitmap(R.drawable.super_player_walk_left_1);
        playerSprite[35] = makeBitmap(R.drawable.super_player_walk_left_2);
        playerSprite[36] = makeBitmap(R.drawable.super_player_walk_left_3);

        playerSprite[37] = makeBitmap(R.drawable.super_player_walk_right_1);
        playerSprite[38] = makeBitmap(R.drawable.super_player_walk_right_2);
        playerSprite[39] = makeBitmap(R.drawable.super_player_walk_right_3);

        //Attack
        playerSprite[40] = makeBitmap(R.drawable.super_player_attack_left_1);
        playerSprite[41] = makeBitmap(R.drawable.super_player_attack_left_2);

        playerSprite[42] = makeBitmap(R.drawable.super_player_attack_right_1);
        playerSprite[43] = makeBitmap(R.drawable.super_player_attack_right_2);

        //Jump
        playerSprite[44] = makeBitmap(R.drawable.super_player_jump_left);
        playerSprite[45] = makeBitmap(R.drawable.super_player_jump_right);

        //Attacked
        playerSprite[46] = makeBitmap(R.drawable.super_player_attacked_left);
        playerSprite[47] = makeBitmap(R.drawable.super_player_attacked_right);

        /*----------------------------------------------------------------------------------------*/

    }

    //Make Bitmap
    private Bitmap makeBitmap(int bitmapId){ return BitmapFactory.decodeResource(getResources(), bitmapId); }
}
