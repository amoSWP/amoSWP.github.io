package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Jet {
	
	
	Texture texture = new Texture(Gdx.files.internal("data/jet.png"));
	float[] d;
	float maxSpeed=20;
	
	
	
	public Jet(){
		d = new float[]{0,0};
	}
	
	public void decay(){
		d[0]*=9.0/10;
		d[1]*=9.0/10;
	}

	public void fly(int i) {
		if(i==0){ // fliege nach links
			d[0]+=-maxSpeed;
			d[1]+=0;
		}
		else if(i==1){ 
			d[0]+=0;
			d[1]+=maxSpeed;
		}
		else if(i==2){
			d[0]+=maxSpeed;
			d[1]+=0;
		}
		else if(i==3){
			d[0]+=0;
			d[1]+=-maxSpeed;
		}
		norm();
	}
	
	public void norm(){
		float s = this.speed();
		if(s>maxSpeed){
			d[0]*=maxSpeed/s;
			d[1]*=maxSpeed/s;
		}
	}

	public float speed(){
		return (float) Math.sqrt(d[0]*d[0]+d[1]*d[1]);
	}
	

}
