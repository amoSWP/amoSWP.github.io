package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;

public class World {
	
	private ArrayList<GameObject> objects;	//Liste aller im Spiel aktiven Objekte
	private float speed;					//Spielgeschwindigkeit in (% der Darstellungsbreite pro Sek.)
	private ObjectGenerator objectGen;		//der Objekt-Generator, welcher die SPielwelt erzeugt (wird im Konstruktor übergeben)
	private GameScreen screen;				//Darstellungsbereich
	private Diver diver;					//der Diver (wird im Konstruktor erstellt)
	
	
	
	public World(ObjectGenerator objectGen, GameScreen screen, float iniSpeed){
		
		objects = new ArrayList<GameObject>();
		speed = iniSpeed;
		
		this.objectGen = objectGen;
		this.screen = screen;
		
		diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 200, screen);
		
	}
	
	
	
	public void draw(Batch batch){			//Ale Spielobjekte zeichnen
		for(GameObject o: objects){o.draw(batch);}
		diver.draw(batch);
	}
	
	public void move(float deltaTime){
		diver.move(deltaTime);
	}
	
	public void resize(){
		diver.resize();
	}

}
