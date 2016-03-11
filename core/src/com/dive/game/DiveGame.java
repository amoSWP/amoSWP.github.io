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
	    
	    @Override
	    public void create() {        
	        float w = Gdx.graphics.getWidth();
	        float h = Gdx.graphics.getHeight();
	        
	        batch = new SpriteBatch();
	        diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 200);
	        
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
	        
	        batch.begin();
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
