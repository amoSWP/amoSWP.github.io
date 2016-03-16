package com.dive.game;

import java.util.ArrayList;

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
	private ObjectGenerator newObjects;
	private World world;
	private GameScreen screen;
	private GameState gameState;
	private float deltaTime, pauseCD;
	private BitmapFont font;

	@Override
	public void create() {

		batch = new SpriteBatch();
		
		font = new BitmapFont();
        font.setColor(Color.RED);

		screen = new GameScreen(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),0,0);
		
		gameState = new GameState(1);
		newObjects = new ObjectGenerator(8,8,0.1f, screen);
		world = new World(newObjects,screen,0.1f,gameState,font);
		
		pauseCD = 0;

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		Assets.getInstance().dispose();
	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		
		//Spiellogik updaten und Welt bewegen
		if(gameState.isRunning()){
			world.update(deltaTime);
			world.move(deltaTime);
		}

		//Spiel pausieren
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && pauseCD <= 0){
			gameState.toggle();pauseCD=0.1f;
		}
		pauseCD-=deltaTime;
		
		//batch erstellen
		batch.begin();
			world.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		screen.setSize(width, height);
		world.resize();
	}

	@Override
	public void pause() {
		gameState.pause();
	}

	@Override
	public void resume() {
		gameState.resume();
	}

}
