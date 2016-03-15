package com.dive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class ObjectGenerator {
	private Shark[] listSharks;
	private float countDown;
	private float maxCountDown;
	private int pointer;

	private int maxNoShark;
	private int min = 70;
	private int max = 390;

	// constructor: kreiere Liste mit Haien
	public ObjectGenerator(int maxNoShark) {
		pointer = 0;
		this.maxNoShark = maxNoShark;
		countDown = maxCountDown = 1;
		listSharks = new Shark[maxNoShark];
		for (int i = 0; i < maxNoShark; i++) {
			listSharks[i] = new Shark(Gdx.graphics.getWidth(), randomInteger(min,
					max), 100, 100, -3.0f, Assets.getInstance().ente);
		}
	}

	// gehe Liste der Haie durch und erstelle neue Liste von Haien welche genau
	// so gezeichnet werden soll
	public void nextObject(ArrayList<Shark> list, float deltaTime) {
		countDown -= deltaTime;
		
		// überprüft ob Zeit abgelaufen und Objekt nicht aktiv, schreibt in
		// Liste um dann gezeichnet zu werden
		if (countDown < 0 && !listSharks[pointer].active) {
			list.add(listSharks[pointer]);

			listSharks[pointer].active = true;
			pointer = (pointer + 1) % maxNoShark;
			countDown = maxCountDown + randomInteger(0, 2);
		}

		// wenn Objekt Bildschirmrand erreicht wird es aus Liste gestrichen, auf
		// Ausgangsposition gesetzt und Status auf nicht aktiv, steht nun wieder
		// zur Verfügung
		for (int i = 0; i < maxNoShark; i++) {
			Shark e = listSharks[i];
			if (e.getActive() && (e.getSprite().getX() < -e.getSprite().getWidth())) {

				e.setActive(false);
				list.remove(e);
				e.getSprite().setX(Gdx.graphics.getWidth());
				e.getSprite().setY(randomInteger(min, max));
				int newSize = randomInteger(50,120);
				e.getSprite().setSize(newSize, newSize);
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
