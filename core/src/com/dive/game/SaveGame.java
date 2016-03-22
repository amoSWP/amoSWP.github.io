package com.dive.game;

import com.dive.game.Score;
import com.dive.game.Highscores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.charset.Charset;
import com.badlogic.gdx.utils.Json;

public class SaveGame {

	// TODO: Change topten to private
	public Score[] topten;
	private Json serializer;
	
	public SaveGame()
	{
		serializer = new Json();
		serializer.setIgnoreUnknownFields(false);
		
		topten = new Score[10];
	}
	
	public void dumpScores() {
		for (Score current : topten) {
			System.out.println(current.getName() + ": " + current.getScore());
		}		
	}
	
	public void toJson(String location) {
		
		// TODO: Exception handling
		//Path locationPath = Paths.get(location);
		//return serializer.toJson(topten);
		
		String output = serializer.toJson(topten);
		
		try {
			Files.write(
				Paths.get(location), 
				output.getBytes(Charset.forName("UTF-8")), 
				StandardOpenOption.CREATE
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fromJson(String location) {
		
		// TODO: Exception handling
		
		String input;
		try {
			input = new String(Files.readAllBytes(Paths.get(location)));
			topten = serializer.fromJson(Score[].class, input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Score[] topten = {
					new Score(), new Score(), new Score(), new Score(), new Score(), new Score(), new Score(), new Score(), new Score(), new Score()
			};
		}
				
	}
	
	public static void main(String[] args) {
		
		System.out.println("### Creating Highscores ###");
		Score[] topten = {
			new Score("Füü", 999),
			new Score("Bar", 888),
			new Score("Baz", 777)
		};
		Highscores highscores = new Highscores();
		highscores.add(new Score("Füü", 999));
		highscores.add(new Score("Bar", 888));
		highscores.add(new Score("Baz", 777));
				
		String location = new String("/home/tonn/Desktop/highscores.json");
		
		SaveGame saveGame = new SaveGame();
		saveGame.topten = topten;
		
		System.out.println("### Highscores ###");
		
		//saveGame.dumpScores();
		System.out.println(highscores);
		
		System.out.println("### Saving files to '" + location + "' ###");
		
		saveGame.toJson(location);
		
		System.out.println("### Reading file from '" + location + "' ###");
		saveGame.fromJson(location);
		
		System.out.println("### Highscores ###");
		
		saveGame.dumpScores();
	}
	
}
