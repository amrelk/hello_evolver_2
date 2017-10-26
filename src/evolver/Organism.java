package evolver;

import java.util.Comparator;
import java.util.Random;

public class Organism {
	private String DNA;
	private Random random;
	
	public Organism(int DNASize) {
		DNA = randomDNA(DNASize);
		random = new Random();
	}
	
	private String randomDNA(int DNASize) {
		StringBuilder tempDNA = new StringBuilder();
		for (int i = 0; i < DNASize; i++) {
			tempDNA.append( (char)(Math.floor(94 * Math.random() + 32)) );
		}
		return tempDNA.toString();
	}
	
	public void mutate(double chance) {
		StringBuilder tempDNA = new StringBuilder(DNA);
		for (int i = 0; i < tempDNA.length(); i++) {
			if (Math.random() < chance) {
				tempDNA.setCharAt(i, (char)( ((int)tempDNA.charAt(i)) + Math.floor(random.nextGaussian()) ) );
			}
		}
		DNA = tempDNA.toString();
	}
	
	public String getDNA() {
		return DNA;
	}
	
	public int score(String target) {
		char[] targeted = target.toCharArray();
		char[] dna = DNA.toCharArray();
		int score = 0;
		
		for (int i = 0; i < targeted.length; i++) {
			score += Math.abs((int)targeted[i] - (int)dna[i]);
		}
		return score;
	}
	
	public String breed(Organism other) {
		StringBuilder a = new StringBuilder(DNA);
		StringBuilder b = new StringBuilder(other.getDNA());
		StringBuilder c = new StringBuilder();
		
		for (int i = 0; i < a.length(); i++) {
			if (Math.random() < .5) {
				c.append(a.charAt(i));
			} else {
				c.append(b.charAt(i));
			}
		}
		return c.toString();
	}
	
}

class Sorter implements Comparator<Organism> {
	String target;
	
	public Sorter(String targeted) {
		target = targeted;
	}
	
	public int compare(Organism a, Organism b) {
		if (a.score(target) > b.score(target)) {
			return 1;
		} else if (a.score(target) < b.score(target)) {
			return -1;
		}
		return 0;
	}
}