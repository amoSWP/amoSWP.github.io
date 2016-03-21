package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Sound;

// From https://github.com/libgdx/libgdx-demo-superjumper/blob/master/core/src/com/badlogicgames/superjumper/Assets.java
// Extended by Singleton-mechanics

public class Assets {
	
	// Assets is Singleton
	
	private static Assets instance = null;
	
	/*
	 * 	TEXTUREN
	 */
	public Texture backgroundBottom;
	public Texture backgroundMiddle;
	public Texture backgroundTop;
	public Texture background;
	public Texture diver;
	public Texture shark;
	public Texture jellyfish;
	public Texture plant;
	public Texture joystickup;
	public Texture joystickunder;
	public Texture apple;
	public Texture paper;
	public Texture oil;
	public Texture boat;
	public Texture black;
	public Texture restartButton;
	public Texture restartButton_hover;
	public Texture startButton;
	public Texture menuButton;
	public Texture air;
	public Texture air_green;
	public Texture air_orange;
	public Texture air_red;
	public BitmapFont font;
	
	//public TextureRegion backgroundRegion;
	
	/*
	 * 	SOUNDS
	 */
	//public Sound makeSomeNoise;
	
	protected Assets() {
		// Empty
	}
	
	public static Assets getInstance() {
		if (instance == null) {
			instance = new Assets();
			instance.load();
		}
		return instance;
	}

	private static Texture loadTexture (String location) {
		// Falls exceptions auftreten, die besagen, dass eine Datei nicht
		// gefunden wurde, ersetze die folgende Zeile mit der nachfolgenden.
		return new Texture(Gdx.files.internal(location));
		//return new Texture(location);
	}

	private void load () {
		
		System.out.println("Loading Assets..");
		
		backgroundBottom = loadTexture("background/sandBackgrounds.png");
		backgroundMiddle = loadTexture("background/mainBackground.png");
		backgroundTop    = loadTexture("background/topBackground.png");
		background		 = loadTexture("background/mainBackground.png");
		diver 			 = loadTexture("diver/diver.png");
		shark			 = loadTexture("Hindernisse/myshark.png");
		jellyfish 		 = loadTexture("Hindernisse/qualle.png");
		plant			 = loadTexture("Hindernisse/green-plant.png");
		joystickup       = loadTexture("joystick/Joystickup.png");
		joystickunder    = loadTexture("joystick/Joystickunder.png");
		apple 			 = loadTexture("Garbage/apple.png");
		paper			 = loadTexture("Garbage/garbage.png");
		oil				 =  loadTexture("Garbage/oil.png");
		boat			= loadTexture("Hindernisse/ship.png");
		black			 = loadTexture("background/black.png");
		restartButton	 = loadTexture("Gamescreens/restartbutton.png");
		restartButton_hover	 = loadTexture("Gamescreens/restartbutton_hover.png");
		startButton		 = loadTexture("Gamescreens/startbutton.png");
		menuButton		 = loadTexture("Gamescreens/menubutton.png");
		air				 = loadTexture("air/air.png");
		air_green		 = loadTexture("air/green.png");
		air_orange		 = loadTexture("air/orange.png");
		air_red			 = loadTexture("air/red.png");
		font 			 = new BitmapFont(Gdx.files.internal("fonts/StS.fnt"));
	}

	public void dispose() {
		Texture[] tex = new Texture[]{backgroundMiddle, background, diver, plant, boat, shark, jellyfish, joystickup, joystickunder, apple, paper, oil, air,air_green,air_orange,air_red, black,restartButton,restartButton_hover, startButton, menuButton};
		for(Texture t:tex){
			t.dispose();
		}
		font.dispose();
	}

	//public static void playSound (Sound sound) {
	//	if (Settings.soundEnabled) sound.play(1);
	//}
}
