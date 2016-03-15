package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;

public class Collision {
	
	public static ObjectType checkCollision(Diver diver, ArrayList<GameObject> objects ){
		for (GameObject o: objects){
			// falls eine Kollision vorliegt wird die Schleife abgebrochen und die Art zurückgegeben
			if(Intersector.overlaps(o.getShape(), diver.getShape())){
				return o.getType();
			}
		}
		return null;
	}
}
