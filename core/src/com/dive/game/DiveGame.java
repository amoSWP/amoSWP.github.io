package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
<<<<<<< HEAD
import com.badlogic.gdx.InputAdapter;
=======
>>>>>>> b72c994d093533fb9908fd674128274a524c931b
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
<<<<<<< HEAD
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
=======
>>>>>>> b72c994d093533fb9908fd674128274a524c931b
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;




public class DiveGame extends ApplicationAdapter implements InputProcessor,ApplicationListener{

	public boolean Android;
	private SpriteBatch batch;
	private ObjectGenerator newObjects;
	private World world;
	private GameState gameState;
	private float deltaTime, pauseCD;
	private BitmapFont font;
	private ArrayList<InputProcessor> processors;

	private float widthScale; //breite der black bars 
	private Sprite bb1, bb2; //blackBars für horizontales 16:9
	private EndScreen endscreen;
	private EndscreenProcessor processor;

	private Stage stage;
	private Joystick joystick;
	
	private OrthographicCamera cam;
	
	
	//Konstruktor für Android
	public DiveGame(boolean Android) {
		this.Android = Android;
	}
	//Konstruktor für alles andere :P
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
		newObjects = new ObjectGenerator(8,8,8,8, 0.1f);
		world = new World(newObjects,0.1f,gameState, font);
		pauseCD = 0;
		
		stage = new Stage();
		joystick = new Joystick();
		if (Android){stage.addActor(joystick.getJoystick());}		
		
		cam = new OrthographicCamera(1920, 1920 * (h / w));
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();
        
		bb1 = new Sprite(Assets.getInstance().black);
		bb2 = new Sprite(Assets.getInstance().black);
		
        
		endscreen = new EndScreen(0);
		processor = new EndscreenProcessor(world, endscreen, gameState);
		processors = new ArrayList<InputProcessor>();
		processors.add(processor);
		processors.add(stage);
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(processor);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		Assets.getInstance().dispose();
		stage.dispose();
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
			world.move(deltaTime, Android, joystick.getJoystick().getKnobPercentX(),joystick.getJoystick().getKnobPercentY());
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
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
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
	
	@Override
	public boolean keyDown(int keycode) {
		for(InputProcessor p: processors){
			p.keyDown(keycode);
		}
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		for(InputProcessor p: processors){
			p.keyUp(keycode);
		}
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		for(InputProcessor p: processors){
			p.keyTyped(character);
		}
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(InputProcessor p: processors){
			p.touchDown(screenX, screenY, pointer, button);
		}
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for(InputProcessor p: processors){
			p.touchUp(screenX, screenY, pointer, button);
		}
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for(InputProcessor p: processors){
			p.touchDragged(screenX, screenY, pointer);
		}
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		for(InputProcessor p: processors){
			p.mouseMoved(screenX, screenY);
		}
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		for(InputProcessor p: processors){
			p.scrolled(amount);
		}
		return false;
	}

}
