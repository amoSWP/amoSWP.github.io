package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class DiveGame extends ApplicationAdapter{

	public boolean Android;
	private SpriteBatch batch;
	private ObjectGenerator newObjects;
	private World world;
	private GameState gameState;
	private float deltaTime, pauseCD;
	private BitmapFont font;
	private float widthScale; //breite der black bars 
	private Sprite bb1, bb2; //blackBars für horizontales 16:9
	private EndScreen endscreen;
	private EndscreenProcessor processor;
	
	private OrthographicCamera cam;

	public DiveGame(boolean Android) {
		this.Android = Android;
	}
	public DiveGame() {
		this.Android = false;
	}

	@Override
	public void create() {
		
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		
		batch = new SpriteBatch();
		
		font = new BitmapFont();
        font.setColor(Color.RED);
		
		gameState = new GameState(1);
		newObjects = new ObjectGenerator(8,8,8, 0.1f);
		world = new World(newObjects,0.1f,gameState, font);
		pauseCD = 0;
		
		cam = new OrthographicCamera(1920, 1920 * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        
        bb1 = new Sprite(Assets.getInstance().black);
        bb2 = new Sprite(Assets.getInstance().black);
        
        endscreen = new EndScreen(0);
        processor = new EndscreenProcessor(world, endscreen, gameState);
        Gdx.input.setInputProcessor(processor);


	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		Assets.getInstance().dispose();
	}

	@Override
	public void render() {
		
		cam.update();
        batch.setProjectionMatrix(cam.combined);
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
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
			world.draw(batch,Android);
			bb1.draw(batch);
			bb2.draw(batch);
			if(gameState.isEndscreen()){
				endscreen.setScore(2);
				endscreen.draw(batch);
			}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if(width/(float) height < 16.0/9){
			cam.viewportWidth = 1920f;
	        cam.viewportHeight = 1920f * height/width;
	        widthScale = 0;
		}
		else{
			cam.viewportHeight = 1080f;
	        cam.viewportWidth = 1080f * width/height;
	        widthScale = (cam.viewportWidth-1920)/(2*1920);
		}
		bb1.setBounds(-widthScale*1920, 0,widthScale*1920, 1080);
		bb2.setBounds(1920, 0,widthScale*1920, 1080);
        cam.update();
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
