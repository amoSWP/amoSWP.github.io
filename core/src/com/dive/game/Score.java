package com.dive.game;

import java.lang.Comparable;
import java.lang.NullPointerException;

public class Score implements Comparable<Score> {
	
		private String name;
		private Integer score;
		
		public Score() {
			this.name = new String("---");
			this.score = new Integer(0);
		}
		
		public Score(Score other) {
			this.name = other.getName();
			this.score = other.getScore();
		}
		
		public Score(String name, Integer score) {
			this.name = name;
			this.score = score;
		}
		
		public String getName() {
			return name;
		}
		
		public Integer getScore() {
			return score;
		}
		
		@Override
		public int compareTo(Score other) {
			if (other == null) {
				throw new NullPointerException("Compare Score instance to null");
			}			
			return score.compareTo(other.getScore());
		}
		
		public String toString() {
			return new String(name + ": " + score);
		}

}
