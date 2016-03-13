package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

// From https://github.com/libgdx/libgdx-demo-superjumper/blob/master/core/src/com/badlogicgames/superjumper/Assets.java
// Extended by Singleton-mechanics

public class Assets {
	
	// Assets is Singleton
	
	private static Assets instance = null;
	
	/*
	 * 	TEXTUREN
	 */
	public Texture backgroundFront;
	public Texture backgroundMiddle;
	public Texture backgroundRear;
	public Texture background;
	public Texture diver;
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
		return new Texture(Gdx.files.internal(location));
		//return new Texture(location);
	}

	private void load () {
		
		System.out.println("Loading Assets..");
		
		backgroundFront  = loadTexture("background/BottomBackground.png");
		backgroundMiddle = loadTexture("background/MainBackground.jpg");
		backgroundRear   = loadTexture("background/TopBackground 2.png");
		background		 = loadTexture("background/backgroundExpanded.png");
		diver 			 = loadTexture("diver/diver.png");
		
	}

	//public static void playSound (Sound sound) {
	//	if (Settings.soundEnabled) sound.play(1);
	//}
}
