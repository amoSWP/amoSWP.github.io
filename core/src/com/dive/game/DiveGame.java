package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DiveGame extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private ObjectGenerator newObjects;
	private World world;
	private GameScreen screen;
	private GameState gameState;
	private float deltaTime, pauseCD;

	@Override
	public void create() {

		batch = new SpriteBatch();

		screen = new GameScreen(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),0,0);
		
		newObjects = new ObjectGenerator(8,8,0.1f, screen);
		world = new World(newObjects,screen,0.1f,gameState);
		gameState = GameState.GAME;
		pauseCD = 0;

	}

	@Override
	public void dispose() {
		batch.dispose();
		Assets.getInstance().dispose();
	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		
		//Spiellogik updaten und Welt bewegen
		if(gameState == GameState.GAME){
			world.update(deltaTime); 
			world.move(deltaTime);
		}
		
		
		//Spiel pausieren
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			if(gameState == GameState.GAME){gameState=GameState.PAUSE; pauseCD=1;}
			else if(gameState == GameState.PAUSE){gameState=GameState.GAME; pauseCD=1;}
			else{pauseCD-=deltaTime;}
		}
		
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
		gameState = GameState.PAUSE;
	}

	@Override
	public void resume() {
	}

}
