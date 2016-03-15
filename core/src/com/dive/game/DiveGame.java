package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DiveGame extends ApplicationAdapter {
	private SpriteBatch batch;

	ObjectGenerator newObjects;
	World world;
	GameScreen screen;
	

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();

		newObjects = new ObjectGenerator(4,4);
		screen = new GameScreen(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),0,0);
		world = new World(newObjects,screen,0.05f);

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

		
		world.move(Gdx.graphics.getDeltaTime());

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
	}

	@Override
	public void resume() {
	}

}
