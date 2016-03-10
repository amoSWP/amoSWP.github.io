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
	    private Texture texture, background, background2;
	    private Sprite sprite, bgSprite, bgSprite2;
	    private Jet jet;
	    
	    @Override
	    public void create() {        
	        float w = Gdx.graphics.getWidth();
	        float h = Gdx.graphics.getHeight();
	        batch = new SpriteBatch();
	        
	        jet = new Jet();
	        
	        texture = new Texture(Gdx.files.internal("data/scubadiver.png"));
	        background = new Texture(Gdx.files.internal("data/background.jpg"));
	        background2 = new Texture(Gdx.files.internal("data/background2.jpg"));
	        
	        bgSprite = new Sprite(background);
	        bgSprite.setPosition(0,0);
	        bgSprite.setSize(w,h);
	        
	        bgSprite2 = new Sprite(background2);
	        bgSprite2.setPosition(w,0);
	        bgSprite2.setSize(w,h);
	        
	        sprite = new Sprite(texture);
	        sprite.setSize(80, 40);
	        sprite.setPosition(w/2 -sprite.getWidth()/2, h/2 - sprite.getHeight()/2);
	        
	    }

	    @Override
	    public void dispose() {
	        batch.dispose();
	        texture.dispose();
	    }

	    @Override
	    public void render() {        
	        Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
	        jet.decay();
	        
	        
	        
	        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && sprite.getX()>0 ){
	            jet.fly(0);
	        }
	        if(Gdx.input.isKeyPressed(Input.Keys.UP) && sprite.getY()<Gdx.graphics.getHeight()-sprite.getHeight()){
	        	jet.fly(1);
	        }
	        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && sprite.getX()<Gdx.graphics.getWidth()-sprite.getWidth()){
	        	jet.fly(2);
	        }
	        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && sprite.getY()>0){
	        	jet.fly(3);
	        }
	        
	        sprite.translate(jet.d[0],jet.d[1]);
	        
	        if(sprite.getX()<=0 && jet.d[0]<0){
	        	jet.d[0]*=-1;
	        }
	        if(sprite.getX()>=Gdx.graphics.getWidth()-sprite.getWidth() && jet.d[0]>0){
	        	jet.d[0]*=-1;
	        }
	        if(sprite.getY()<=0 && jet.d[1]<0){
	        	jet.d[1]*=-1;
	        }
	        if(sprite.getY()>=Gdx.graphics.getHeight()-sprite.getHeight() && jet.d[1]>0){
	        	jet.d[1]*=-1;
	        }
	        
	        bgSprite.translateX(-1);
	        bgSprite2.translateX(-1);
	        
	        if(bgSprite.getX()+bgSprite.getWidth() < 0){
	        	bgSprite.setX(Gdx.graphics.getWidth());
	        }
	        if(bgSprite2.getX()+bgSprite2.getWidth() < 0){
	        	bgSprite2.setX(Gdx.graphics.getWidth());
	        }
	        
	        batch.begin();
	        bgSprite.draw(batch);
	        bgSprite2.draw(batch);
	        sprite.draw(batch);
	        batch.end();
	    }

	    @Override
	    public void resize(int width, int height) {
	    }

	    @Override
	    public void pause() {
	    }

	    @Override
	    public void resume() {
	    }
	
}
