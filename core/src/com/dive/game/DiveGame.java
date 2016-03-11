package com.dive.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	    
	    @Override
	    public void create() {        
	        float w = Gdx.graphics.getWidth();
	        float h = Gdx.graphics.getHeight();
	        
	        batch = new SpriteBatch();
	        diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 200);
	        background1 = new Sprite(Assets.getInstance().background);
	        background2 = new Sprite(Assets.getInstance().background);
	        
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
	        
	        batch.begin();
	        	background1.draw(batch);
	        	background2.draw(batch);
	        	diver.draw(batch, Gdx.graphics.getDeltaTime());
	        batch.end();
	    }

	    @Override
	    public void resize(int width, int height) {
	    	
	    	diver.setWindow(width, height);
	    	
	    	
	    	
	    }

	    @Override
	    public void pause() {
	    }

	    @Override
	    public void resume() {
	    }
	
}
