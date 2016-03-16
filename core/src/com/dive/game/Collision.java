package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;

public class Collision {

	private ObjectType result ;
	
	public ObjectType checkCollision(Diver diver, ArrayList<GameObject> objects ){
		result = null;
		for (GameObject o: objects){
			// falls eine Kollision vorliegt wird die Schleife abgebrochen und die Art zurückgegeben
			if(Intersector.overlaps(o.getShape(), diver.getShape())){
				result = o.getType();
				break;
			}
		}
		return result;
	}
}
