package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;

public class World {
	
	private ArrayList<GameObject> objects;	//Liste aller im Spiel aktiven Objekte
	private float speed;					//Spielgeschwindigkeit in (% der Darstellungsbreite pro Sek.)
	private ObjectGenerator objectGen;		//der Objekt-Generator, welcher die SPielwelt erzeugt (wird im Konstruktor übergeben)
	private GameScreen screen;				//Darstellungsbereich
	private Diver diver;					//der Diver (wird im Konstruktor erstellt)
	private Parallax parallax;				//Der Hintergrund mit Parallax Effekt
	private GameState state;				//setzt den SPielzustand (zB um zu pausieren)
	
	
	
	public World(ObjectGenerator objectGen, GameScreen screen, float iniSpeed, GameState state){
		
		objects = new ArrayList<GameObject>();
		speed = iniSpeed;
		
		this.objectGen = objectGen;
		this.screen = screen;
		this.state = state;
		
		diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 300, screen);
		parallax = new Parallax(speed, screen);
		
	}
	
	
	
	public void draw(Batch batch){			//Alle Spielobjekte zeichnen
		parallax.draw(batch);
		for(GameObject o: objects){o.draw(batch);}
		diver.draw(batch);
		
	}
	
	public void move(float deltaTime){
		for(GameObject o: objects){o.moveObject(screen.width,deltaTime, speed);}
		diver.move(deltaTime);
		parallax.move(deltaTime);
	}
	
	public void resize(){
		diver.resize();
	}
	
	public void update(float deltaTime){
		//Diver auf Standardgeschwindigkeit (nachdem er verlangsamt wurde durch kollision)
		diver.refresh();
		
		//Level aufbauen
		objectGen.nextPlant(objects, deltaTime);
		objectGen.nextShark(objects, deltaTime);
		
		//Kollisionsabfragen
		ObjectType coll = Collision.checkCollision(diver, objects);
		if(coll == ObjectType.SHARK){state.pause();}
		else if(coll == ObjectType.PLANT){diver.slow();}
	}

}
