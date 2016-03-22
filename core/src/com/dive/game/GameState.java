package com.dive.game;

public class GameState {
	
	private State state;
	
	public GameState(){
		state = State.MENU;
	}
	
	public GameState(int i){
		switch(i){
			case 0: state = State.MENU; break;
			case 1: state = State.GAME; break; 
			default : state = State.MENU; break;
		}
	}
	
	public State getState(){
		return state;
	}
	
	public void pause(){
		state = State.PAUSE;
	}
	
	public void resume(){
		state = State.GAME;
	}
	
	public void returnMenu(){
		state = State.MENU;
	}
	
	public void seeScores(){
		state = State.HIGHSCORES;
	}
	
	public void toggle(){
		if(state == State.GAME){state=State.PAUSE;}
		else if(state == State.PAUSE){state=State.GAME;}
	}
	
	public void gameOver(){
		state = State.ENDSCREEN;
	}
	
	public boolean isRunning(){
		return (state == State.GAME);
	}
	
	public boolean isEndscreen(){
		return (state == State.ENDSCREEN);
	}
	
	
	
}

