package evolver;

import java.util.Arrays;

public class Evolver {
	
	static String target = "Hello, I am malcolm.";
	static int popsize = 10;
	
	public static void main(String[] args) {
		Sorter sorter = new Sorter(target);
		Organism[] population = new Organism[popsize];
		for (int i = 0; i < population.length; i++) {
			population[i] = new Organism(target.length());
		}
		for (int i = 0; i < population.length; i++) {
			System.out.print(population[i].getDNA());
			System.out.print(" : ");
			System.out.println(population[i].score(target));
		}
		Arrays.sort(population, sorter);
		System.out.println("\nSORTED\n");
		for (int i = 0; i < population.length; i++) {
			System.out.print(population[i].getDNA());
			System.out.print(" : ");
			System.out.println(population[i].score(target));
		}
	}
	
}
