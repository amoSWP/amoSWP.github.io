package com.dive.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DiveGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture background;
	private Texture divertex;
	private Sprite diversprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		// Background
		background = new Texture("Background.jpg");
		// Moveable Objects
		divertex = new Texture(Gdx.files.internal("jet.png"));
		diversprite = new Sprite(divertex);
	}	

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Draw Background
		batch.draw(background, 0, 0);
		// Movement of Jet
		// Movement Up
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
        	// If Control is pressed move 1 pixel else 10
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                diversprite.translateY(1f);
            else
                diversprite.translateY(10.0f);
        }
        // Movement Down
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	// If Control is pressed 1 pixel else 10
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                diversprite.translateY(-1f);
            else
                diversprite.translateY(-10.0f);
        }
		batch.begin();		
		diversprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void resize(int width, int height){	
	}
	
	@Override
	public void pause(){
	}
	
	@Override
	public void resume(){	
	}
}
