package com.dive.game;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.dive.game.Score;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Highscores {
	
	//private Score[] highscores;
	private PriorityQueue<Score> highscores; 

	public Highscores() {
		//highscores = new Score[10];
		highscores = new PriorityQueue<Score>();
	}
	
	public Highscores(String json) {
		// TODO: Construct from JSON?
		// highscores = new PriorityQueue<Score>(HERE BE Score[] array);
		throw new NotImplementedException();
	}
	
	public Score[] get() {
		// Overload of get function. Default behaviour returns top ten highscores
		return get(10);
	}
	
	public Score[] get(int n) {
		// Instantiate a Score-Array create a copy of the highscores priority queue
		Score[] topten = new Score[n];
		PriorityQueue<Score> tmp = new PriorityQueue<Score>(highscores);
		
		// Poll (Return and remove) head of the queue
		for (int idx=0; idx<n; idx++) {
			Score currentScore = tmp.poll();
			if (currentScore == null) {
				// Poll on an empty queue defaults to null. In this case construct an 
				// empty Score
				topten[idx] = new Score();
			}
			else {
				topten[idx] = currentScore;
			}
		}
		
		return topten;
	}
	
	public int size() {
		return highscores.size();
	}
	
	public void reset() {
		highscores.clear();
	}
	
	public void add(Score score) {
		highscores.add(score);
	}	
	
	public String toString() {
		String output = new String();
		for (Score elem : get(size())) {
			// Apparently, %%n indicates a platform independent newline in Java
			output += String.format(elem.toString() + System.lineSeparator());
		}
		return output;
	}
	
	
}
