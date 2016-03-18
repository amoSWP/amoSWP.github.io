package com.dive.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;





public class DiveGame extends ApplicationAdapter implements ApplicationListener {

	public boolean Android;
	private SpriteBatch batch;
	private ObjectGenerator newObjects;
	private World world;
	private GameScreen screen;
	private GameState gameState;
	private float deltaTime, pauseCD;
	private BitmapFont font;
	private Stage stage;
	
	private Touchpad joystick;
	private Drawable knob;
	private Drawable background;
	private TouchpadStyle joystickstyle;
	private Skin skin;
	
	private OrthographicCamera camera;
	private Texture blockTexture;
	private Sprite blockSprite;
	private float blockSpeed;
	
	
	
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
		stage = new Stage();
		
		font = new BitmapFont();
        font.setColor(Color.RED);

		screen = new GameScreen(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),0,0);
		
		gameState = new GameState(1);
		newObjects = new ObjectGenerator(8,8,8, 0.1f, screen);
		world = new World(newObjects,screen,0.1f,gameState, font);
		pauseCD = 0;
		
		
		
		skin = new Skin();										//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		skin.add("background",Assets.getInstance().joystickunder);
    		skin.add("knob",Assets.getInstance().joystickup);
		background = skin.getDrawable("background");
    		knob = skin.getDrawable("knob");
    		joystickstyle = new TouchpadStyle(background,knob);		//Joystickstyle wird erstellt bekommt seine Drawables
    	
		knob.setMinWidth(screen.width/8);						//Größe des Joysticks
		knob.setMinHeight(screen.width/8);
		
		joystick = new Touchpad(5,joystickstyle);	//Joystick wird erstellt mit Bewegungsradius des Knüppels = 1/10 des Bildschirms
		joystick.setBounds(3*screen.width/4,  0 , screen.width/4, screen.width/4);//Größe und Platzierung des Joystickpads
		if (Android){stage.addActor(joystick);}		
		Gdx.input.setInputProcessor(stage);
		

		
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
			world.move(deltaTime, Android, joystick.getKnobPercentX(),joystick.getKnobPercentY());
		}

		//Spiel pausieren
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && pauseCD <= 0){
			gameState.toggle();pauseCD=0.1f;
		}
		pauseCD-=deltaTime;
		

		//batch erstellen
		batch.begin();
		world.draw(batch,Android);
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
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
