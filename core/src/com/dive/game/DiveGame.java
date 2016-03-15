package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class DiveGame extends ApplicationAdapter {
	 private SpriteBatch batch;
	    private Diver diver;
	    private Sprite background1, background2;
	    
	    Ente Ente1, Ente2, Ente3, Wal1;
	    Texture textureEnte, textureWal;
	    
	    Shark shark;
	    GameScreen screen;
	    
	    int max = 450;
	    
	    @Override
	    public void create() {        
	        float w = Gdx.graphics.getWidth();
	        float h = Gdx.graphics.getHeight();
	        
	        screen = new GameScreen(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),0,0);
	        shark = new Shark(new Sprite(Assets.getInstance().diver), new float[]{0,0}, ObjectType.SHARK, 0.5f, 0.5f, 0.1f, 0.1f,screen);
	        
	        batch = new SpriteBatch();
	        diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 200);
	        background1 = new Sprite(Assets.getInstance().background);
	        background2 = new Sprite(Assets.getInstance().background);
	        
			textureEnte = new Texture("ente.png");
			textureWal = new Texture("wal.png");
			
			Ente1 = new Ente(700, randomInteger(max), 100, 100, -2.0f, textureEnte);
			Ente2 = new Ente(700, randomInteger(max), 50, 50, -1.5f, textureEnte);
			Ente3 = new Ente(700, randomInteger(max), 80, 80, -1.5f, textureEnte);
			Wal1  = new Ente(700, randomInteger(max), 80, 80, -1.5f, textureWal);
	        
	        background1.setX(0);
	        background1.setSize(2*w, h);
	        background2.setX(2*w);
	        background2.setSize(2*w, h);
	        
	    }

	    @Override
	    public void dispose() {
	        batch.dispose();
	    }

	    @Override
	    public void render() {        
	        Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
	        diver.onKeystroke();
	        
	        background1.translateX(-5);
	        background2.translateX(-5);
	        if(background1.getX()+background1.getWidth() <0){
	        	background1.setX(background2.getX()+background2.getWidth());
	        }
	        if(background2.getX()+background2.getWidth() <0){
	        	background2.setX(background1.getX()+background1.getWidth());
	        }
	        
	        // Move the duck before batch.begin()
	        Ente1.moveEnte(Ente1, 700, randomInteger(max));
			Ente2.moveEnte(Ente2, 700, randomInteger(max));
			Ente3.moveEnte(Ente3, 700, randomInteger(max));
			Wal1.moveEnte(Wal1, 700, randomInteger(max));
	        
	        batch.begin();
	        background1.draw(batch);
	        background2.draw(batch);
	        	
	        // draws moving duck
		 	// Ente1.sprite.translateX(Ente1.enteSpeed);
			Ente1.getSprite().draw(batch);
			Ente2.getSprite().draw(batch);
			Ente3.getSprite().draw(batch);
		 	Wal1.getSprite().draw(batch);
		 	shark.draw(batch);
	        	
	        diver.draw(batch, Gdx.graphics.getDeltaTime());
	        batch.end();
	    }

	    @Override
	    public void resize(int width, int height) {
	    	
	    	diver.setWindow(width, height);
	    	shark.resize(0.99f);
	    	
	    	
	    }

	    @Override
	    public void pause() {
	    }

	    @Override
	    public void resume() {
	    }
	

	
public int randomInteger(int max) {
    Random rand = new Random();
    int randomNum = (int)(Math.random() * ((max) + 1));
    return randomNum;
}

}
