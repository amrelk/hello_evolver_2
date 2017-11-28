package evolver;

import java.util.Arrays;
import java.util.Random;
import java.time.Instant;

public class Population {
	private Organism[] population;
	private Random random;
	private Sorter sorter;
	
	public Population(int popSize) {
		population = new Organism[popSize];
		for (int i = 0; i < popSize; i++) {
			population[i] = new Organism(Evolver.target.length());
		}
		random = new Random();
		sorter = new Sorter(Evolver.target);
	}
	
	/**
	 * Fills the bottom half of the population with the breedings of the top half.
	 */
	public void breed() {
		Arrays.sort(population, sorter);
		for (int i = 0; i < population.length / 2; i++) {
			population[i + population.length/2].setDNA(population[i].breed(population[random.nextInt(population.length/2)]));
		}
	}
	
	/**
	 * Displays the entire population. Why would you want to do this? I don't know.
	 */
	public void displayPop() {
		for (int i = 0; i < population.length; i++) {
			System.out.println(population[i].getDNA() + " : " + population[i].score());
		}
	}
	
	/**
	 * Displays the best Organism in the population, along with its score.
	 * 
	 * @output "{DNA of best Organism} : {score of best Organism}"
	 */
	public void displayBest() {
		Arrays.sort(population, sorter);
		System.out.println(population[0].getDNA() + " : " + population[0].score());
	}
	
	/**
	 * Displays the best Organism in the population, along with its score.
	 * 
	 * @param starttime the starting time, as an Instant
	 * 
	 * @output "{DNA of best Organism} : {score of best Organism}	: {time elapsed}s"
	 */
	public void displayBest(Instant starttime) {
		Arrays.sort(population, sorter);
		System.out.println(population[0].getDNA() + " : " + population[0].score() + "\t: " + ((float)Instant.now().minusMillis(starttime.toEpochMilli()).toEpochMilli() / 1000) + "s");
	}
	
	/**
	 * @return the best score from the population
	 */
	public int bestScore() {
		Arrays.sort(population, sorter);
		return population[0].score();
	}
	
	/**
	 * mutates the population
	 * 
	 * @param rate the rate of mutation, as a decimal
	 */
	public void mutate(double rate) {
		for (int i = 0; i < population.length; i++) {
			population[i].mutate(rate);
		}
	}
}
