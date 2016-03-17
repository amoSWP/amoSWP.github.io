package com.dive.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;


public class Animator extends ApplicationAdapter {

    private static final int        FRAME_COLS = 6;         
    private static final int        FRAME_ROWS = 5;         

    Animation                       diveAnimation;          
    Texture                         diveSheet;              
    TextureRegion[]                 diveFrames;             
    SpriteBatch                     spriteBatch;            
    TextureRegion                   currentFrame;           

    float stateTime;                                        

    @Override
    public void create() {
        diveSheet = new Texture(Gdx.files.internal("spriteAnimation.png"));
        TextureRegion[][] tmp = TextureRegion.split(diveSheet, diveSheet.getWidth()/FRAME_COLS, diveSheet.getHeight()/FRAME_ROWS);                      
        diveFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                diveFrames[index++] = tmp[i][j];
            }
        }
        diveAnimation = new Animation(0.025f, diveFrames);     
        spriteBatch = new SpriteBatch();                
        stateTime = 0f;                        
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        
        stateTime += Gdx.graphics.getDeltaTime();           
        currentFrame = diveAnimation.getKeyFrame(stateTime, true);  
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 50, 50);             
        spriteBatch.end();
    }

}
    