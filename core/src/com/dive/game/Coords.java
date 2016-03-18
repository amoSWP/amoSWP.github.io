package com.dive.game;

import com.badlogic.gdx.Gdx;

public class Coords {

	public static float[] getCameraCoords(int x, int y){		//wandelt absolute Koordinaten (abhängig von echten pixeln) in logische koordinaten der kamera um
		float[] p = new float[]{0,0};
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		float relX = x/w;
		float relY = y/h;
		
		float b;
		
		if(w/h<16f/9f){
			p[0]=relX*1920;
			b = (h-w*9f/16f)/2;
			p[1]=(y-b)/(w*9f/16f)*1080;
		}
		else{
			b = (w-h*16f/9f)/2;
			p[0]=(x-b)/(h*16f/9f)*1920;
			p[1]=relY*1080;
		}
		
		return p;
	}
	
}
