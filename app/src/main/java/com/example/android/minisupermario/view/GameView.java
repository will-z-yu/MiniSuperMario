package com.example.android.minisupermario.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.android.minisupermario.R;
import com.example.android.minisupermario.controller.PlayerController;
import com.example.android.minisupermario.model.Player;

import static android.view.MotionEvent.ACTION_DOWN;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    /*Fields*/
    //"Resolution" of the game
    private final static int vertnum = 6;
    private final static int horinum = 10;
    private final static int maxMoveCount = 5;//change PlayerController
    int width;
    int height;
    int rowHeight;
    int columnWidth;

    //Bitmaps
    private Bitmap background[];
    private Bitmap foreground[];
    private Bitmap buttons[];
    private Bitmap playerSprite[];

    //Player
    private Player Mario;
    private PlayerController player1;

    /*Constructor*/
    public GameView (Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        //Instantiate
        background = new Bitmap[3];
        foreground = new Bitmap[2];
        buttons = new Bitmap[4];
        playerSprite = new Bitmap[48];

        Mario = new Player(29,4);
        player1 = new PlayerController(Mario);
    }

    /*Methods*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int clickX;
        int clickY;
        clickX = (int) event.getX();
        clickY = (int) event.getY();

        //Left
        if(     event.getAction() == ACTION_DOWN &&
                0 < clickX && clickX < columnWidth &&
                rowHeight * (vertnum - 1) < clickY && clickY < height){
            System.out.println("clicked left!");
            player1.moveLeft(columnWidth/maxMoveCount);
        }

        //Right
        if (    event.getAction() == ACTION_DOWN &&
                columnWidth < clickX && clickX < 2 * columnWidth &&
                rowHeight * (vertnum - 1) < clickY && clickY < height) {
            System.out.println("clicked right!");
            player1.moveRight(columnWidth/maxMoveCount);
        }

        //Attack

        //Jump

        return true;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        Rect rect = new Rect();

        //Get screen width and height in order to draw properly
        width = getWidth();
        height = getHeight();
        rowHeight = height/vertnum;
        columnWidth = width/horinum;

        //Set Mario's initial (x,y)
        if(!Mario.reset()){
            Mario.setxPosPixel((horinum - 1) * columnWidth);
            Mario.setyPosPixel((vertnum - 2) * rowHeight);
            Mario.setReset(true);
        }

        //Background
        drawBackground(canvas, rect);

        //Buttons
        drawButtons(canvas, rect);

        //Player
        drawPlayer(canvas, rect, Mario);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);
        //Background
        background[0] = BitmapFactory.decodeResource(getResources(), R.drawable.background_default);
        background[1] = BitmapFactory.decodeResource(getResources(), R.drawable.background_victory);
        background[2] = BitmapFactory.decodeResource(getResources(), R.drawable.background_game_over);

        //Foreground
        foreground[0] = BitmapFactory.decodeResource(getResources(), R.drawable.stage_clear);
        foreground[1] = BitmapFactory.decodeResource(getResources(), R.drawable.game_over);

        //Buttons
        buttons[0] = BitmapFactory.decodeResource(getResources(), R.drawable.button_left);
        buttons[1] = BitmapFactory.decodeResource(getResources(), R.drawable.button_right);
        buttons[2] = BitmapFactory.decodeResource(getResources(), R.drawable.button_a);
        buttons[3] = BitmapFactory.decodeResource(getResources(), R.drawable.button_b);

        /*Player*/
        /*----------------------------------------------------------------------------------------*/
        //Idle
        playerSprite[0] = BitmapFactory.decodeResource(getResources(), R.drawable.player_idle_left);
        playerSprite[1] = BitmapFactory.decodeResource(getResources(), R.drawable.player_idle_right);

        //Move
        playerSprite[2] = BitmapFactory.decodeResource(getResources(), R.drawable.player_walk_left_1);
        playerSprite[3] = BitmapFactory.decodeResource(getResources(), R.drawable.player_walk_left_2);
        playerSprite[4] = BitmapFactory.decodeResource(getResources(), R.drawable.player_walk_left_3);

        playerSprite[5] = BitmapFactory.decodeResource(getResources(), R.drawable.player_walk_right_1);
        playerSprite[6] = BitmapFactory.decodeResource(getResources(), R.drawable.player_walk_right_2);
        playerSprite[7] = BitmapFactory.decodeResource(getResources(), R.drawable.player_walk_right_3);

        //Jump
        playerSprite[8] = BitmapFactory.decodeResource(getResources(), R.drawable.player_jump_left);
        playerSprite[9] = BitmapFactory.decodeResource(getResources(), R.drawable.player_jump_right);

        //Attack
        playerSprite[10] = BitmapFactory.decodeResource(getResources(), R.drawable.player_attack_left_1);
        playerSprite[11] = BitmapFactory.decodeResource(getResources(), R.drawable.player_attack_left_2);

        playerSprite[12] = BitmapFactory.decodeResource(getResources(), R.drawable.player_attack_right_1);
        playerSprite[13] = BitmapFactory.decodeResource(getResources(), R.drawable.player_attack_right_2);

        //Attacked
        playerSprite[14] = BitmapFactory.decodeResource(getResources(), R.drawable.player_attacked_left);
        playerSprite[15] = BitmapFactory.decodeResource(getResources(), R.drawable.player_attacked_right);

        /*----------------------------------------------------------------------------------------*/

        /*Red Player*/
        /*----------------------------------------------------------------------------------------*/
        //Idle
        playerSprite[16] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_idle_left);
        playerSprite[17] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_idle_right);

        //Move
        playerSprite[18] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_walk_left_1);
        playerSprite[19] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_walk_left_2);
        playerSprite[20] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_walk_left_3);

        playerSprite[21] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_walk_right_1);
        playerSprite[22] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_walk_right_2);
        playerSprite[23] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_walk_right_3);

        //Attack
        playerSprite[24] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_attack_left_1);
        playerSprite[25] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_attack_left_2);

        playerSprite[26] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_attack_right_1);
        playerSprite[27] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_attack_right_2);

        //Jump
        playerSprite[28] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_jump_left);
        playerSprite[29] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_jump_right);

        //Attacked
        playerSprite[30] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_attacked_left);
        playerSprite[31] = BitmapFactory.decodeResource(getResources(), R.drawable.red_player_attacked_right);

        /*----------------------------------------------------------------------------------------*/

        /*Super Player*/
        /*----------------------------------------------------------------------------------------*/
        //Idle
        playerSprite[32] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_idle_left);
        playerSprite[33] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_idle_right);

        //Move
        playerSprite[34] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_walk_left_1);
        playerSprite[35] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_walk_left_2);
        playerSprite[36] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_walk_left_3);

        playerSprite[37] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_walk_right_1);
        playerSprite[38] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_walk_right_2);
        playerSprite[39] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_walk_right_3);

        //Attack
        playerSprite[40] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_attack_left_1);
        playerSprite[41] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_attack_left_2);

        playerSprite[42] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_attack_right_1);
        playerSprite[43] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_attack_right_2);

        //Jump
        playerSprite[44] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_jump_left);
        playerSprite[45] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_jump_right);

        //Attacked
        playerSprite[46] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_attacked_left);
        playerSprite[47] = BitmapFactory.decodeResource(getResources(), R.drawable.super_player_attacked_right);

        /*----------------------------------------------------------------------------------------*/

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}

    /*Private Methods*/
    //Draw background
    private void drawBackground(Canvas canvas, Rect rect){
        rect.set(0, 0, width, height);
        canvas.drawBitmap(background[0], null, rect, null);
    }

    //Draw Buttons
    private void drawButtons(Canvas canvas, Rect rect){
        //left
        rect.set(0, (vertnum - 1) * rowHeight, columnWidth, height);
        canvas.drawBitmap(buttons[0], null, rect, null);
        //right
        rect.set(columnWidth, (vertnum - 1) * rowHeight, 2 * columnWidth, height);
        canvas.drawBitmap(buttons[1], null, rect, null);
        //a (attack)
        rect.set((horinum - 2) * columnWidth, (vertnum - 1) * rowHeight, (horinum - 1) * columnWidth, height);
        canvas.drawBitmap(buttons[2], null, rect, null);
        //b (jump)
        rect.set((horinum - 1) * columnWidth, (vertnum - 1) * rowHeight, horinum * columnWidth, height);
        canvas.drawBitmap(buttons[3], null, rect, null);
    }

    //Draw Player
    private void drawPlayer(Canvas canvas, Rect rect, Player player){
        int x = player.getxPosPixel();
        int y = player.getyPosPixel();
        rect.set(x, y, x + columnWidth, y + rowHeight);
        canvas.drawBitmap(playerSprite[player.getSprite()], null, rect, null);
        invalidate();
    }
}
