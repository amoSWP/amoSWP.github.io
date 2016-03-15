package com.dive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class ObjectGenerator {
	Ente[] listEnten;
	float countDown;
	float maxCountDown;
	int pointer;
	private int newSize;

	int maxNoEnte;
	int min = 70;
	int max = 390;

	// constructor: kreiere Liste mit Enten
	public ObjectGenerator(int maxNoEnte) {
		pointer = 0;
		this.maxNoEnte = maxNoEnte;
		countDown = maxCountDown = 1;
		listEnten = new Ente[maxNoEnte];
		for (int i = 0; i < maxNoEnte; i++) {
			listEnten[i] = new Ente(Gdx.graphics.getWidth(), randomInteger(min,
					max), 100, 100, -3.0f, Assets.getInstance().ente);
		}
	}

	// gehe Liste der Enten durch und erstelle neue Liste von Enten welche genau
	// so gezeichnet werden soll
	public void nextObject(ArrayList<Ente> list, float deltaTime) {
		countDown -= deltaTime;
		
		// überprüft ob Zeit abgelaufen und Objekt nicht aktiv, schreibt in
		// Liste um dann gezeichnet zu werden
		if (countDown < 0 && !listEnten[pointer].active) {
			list.add(listEnten[pointer]);

			listEnten[pointer].active = true;
			pointer = (pointer + 1) % maxNoEnte;
			countDown = maxCountDown + randomInteger(0, 2);
		}

		// wenn Objekt Bildschirmrand erreicht wird es aus Liste gestrichen, auf
		// Ausgangsposition gesetzt und Status auf nicht aktiv, steht nun wieder
		// zur Verfügung
		for (int i = 0; i < maxNoEnte; i++) {
			Ente e = listEnten[i];
			if (e.getActive() && (e.sprite.getX() < -e.sprite.getWidth())) {

				e.setActive(false);
				list.remove(e);
				e.sprite.setX(Gdx.graphics.getWidth());
				e.sprite.setY(randomInteger(min, max));
				newSize = randomInteger(50,120);
				e.sprite.setSize(newSize, newSize);
			}
		}
	}

	//Zufällige Erzeugung von integer Werten zwischen min max
	public int randomInteger(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
