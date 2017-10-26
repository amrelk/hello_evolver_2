package evolver;

import java.util.Arrays;
import java.util.Random;

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
	
	public void breed() {
		Arrays.sort(population, sorter);
		for (int i = 0; i < population.length / 2; i++) {
			population[i + population.length/2].setDNA(population[i].breed(population[random.nextInt(population.length/2)]));
		}
	}
	
	public void displayPop() {
		for (int i = 0; i < population.length; i++) {
			System.out.println(population[i].getDNA() + " : " + population[i].score());
		}
	}
	
	public void displayBest() {
		Arrays.sort(population, sorter);
		System.out.println(population[0].getDNA() + " : " + population[0].score());
	}
	
	public int bestScore() {
		Arrays.sort(population, sorter);
		return population[0].score();
	}
	
	public void mutate(double rate) {
		for (int i = 0; i < population.length; i++) {
			population[i].mutate(rate);
		}
	}
}
