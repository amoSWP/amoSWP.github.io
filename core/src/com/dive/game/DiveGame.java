package com.dive.game;


import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class DiveGame extends ApplicationAdapter {

OrthographicCamera camera;
Ente Ente1;
Ente Ente2;
Ente Ente3;
Ente Wal1;
SpriteBatch batch;
Texture textureEnte;
Texture textureWal;

int frameWidth, frameHeight;

int max = 450;

	@Override
	public void create(){
	frameWidth = Gdx.graphics.getWidth();
	frameHeight = Gdx.graphics.getHeight();
	
	textureEnte = new Texture("ente.png");
	textureWal = new Texture("wal.png");
	batch = new SpriteBatch();
	// camera = new OrthographicCamera(300, 480);
	

	Ente1 = new Ente(700, randomInteger(max), 100, 100, -2.0f, textureEnte);
	Ente2 = new Ente(700, randomInteger(max), 50, 50, -1.5f, textureEnte);
	Ente3 = new Ente(700, randomInteger(max), 80, 80, -1.8f, textureEnte);
	Wal1 = new Ente(700, randomInteger(max), 80, 80, -1.5f, textureWal);

	}
	
	
	
		

	@Override
	public void render () {
		// System.out.println(frameWidth);
		// System.out.println(frameHeight);
		

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Gdx.graphics.setWindowedMode(400, 300);
		// camera.update();
		// batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		// movement of the duck

		Ente1.moveEnte(Ente1, 700, randomInteger(max));
		Ente2.moveEnte(Ente2, 700, randomInteger(max));
		Ente3.moveEnte(Ente3, 700, randomInteger(max));
		Wal1.moveEnte(Wal1, 700, randomInteger(max));

		// draws moving duck
		 // Ente1.sprite.translateX(Ente1.enteSpeed);

		 Ente1.getSprite().draw(batch);
		 Ente2.getSprite().draw(batch);
		 Ente3.getSprite().draw(batch);
		 Wal1.getSprite().draw(batch);


		 batch.end();
	}

	
public int randomInteger(int max) {
    Random rand = new Random();
    int randomNum = (int)(Math.random() * ((max) + 1));
    return randomNum;
}

}
