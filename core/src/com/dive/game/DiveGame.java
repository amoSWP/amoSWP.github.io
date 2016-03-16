package com.dive.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class DiveGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Diver diver;
	private Sprite background1, background2;
	private Touchpad joystick;
	private Drawable knob;
	private Drawable background;
	private TouchpadStyle joystickstyle;
	private Skin skin;
	private Stage stage;


	ObjectGenerator newObjects;
	ArrayList<Shark> listSharks;
	
	Texture textureEnte, textureWal;

	int creationPixel = 1400;
	int min = 80;
	int max = 380;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		


		batch = new SpriteBatch();
		diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 200);
		background1 = new Sprite(Assets.getInstance().background);
		background2 = new Sprite(Assets.getInstance().background);

		newObjects = new ObjectGenerator(4);
		listSharks = new ArrayList<Shark>();
		
    	skin = new Skin();
    	skin.add("background",Assets.getInstance().joystickunder);
    	skin.add("knob",Assets.getInstance().joystickup);
		
    	joystickstyle = new TouchpadStyle();
    	background = skin.getDrawable("background");
    	knob = skin.getDrawable("knob");
   
		knob.setMinWidth(20);
		knob.setMinHeight(20);
		
		joystick = new Touchpad(5,joystickstyle);

		background1.setX(0);
		background1.setSize(2 * w, h);
		background2.setX(2 * w);
		background2.setSize(2 * w, h);

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
		//diver.onJoystick();

		background1.translateX(-3);
		background2.translateX(-3);
		if (background1.getX() + background1.getWidth() < 0) {
			background1.setX(background2.getX() + background2.getWidth());
		}
		if (background2.getX() + background2.getWidth() < 0) {
			background2.setX(background1.getX() + background1.getWidth());
		}

		batch.begin();
		background1.draw(batch);
		background2.draw(batch);

		// kreiert List mit zu Verfügung stehenden Objekten (Enten)
		newObjects.nextObject(listSharks, Gdx.graphics.getDeltaTime());

		// gehe List mit aktiven (dh auf dem Bildschrirm erscheinen sollen)
		// Enten durch und bwege, danach zeichne sie
		for (Shark e : listSharks) {
			e.moveShark();
			e.getSprite().draw(batch);
		}
		joystick.setBounds(15, 15, creationPixel, creationPixel);
		joystick.draw(batch, creationPixel);

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
